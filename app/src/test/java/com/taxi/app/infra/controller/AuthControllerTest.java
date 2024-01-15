package com.taxi.app.infra.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import com.taxi.app.domain.enums.AccountType;
import com.taxi.app.dto.AccountLoginRequest;
import com.taxi.app.dto.AccountRequest;
import com.taxi.app.extension.ContainerBaseExtension;
import com.taxi.app.infra.config.security.token.JwtTokenValidator;
import com.taxi.app.infra.entity.AccountEntity;
import com.taxi.app.infra.repository.AccountRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class AuthControllerTest extends ContainerBaseExtension {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void signUp() throws Exception {

        final AccountRequest request = new AccountRequest("name", "email@gmail.com", "44555555555", "1234", AccountType.PASSENGER);

        mockMvc.perform(post("/api/auth/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(request)));

        Assertions.assertThat(accountRepository.findByEmail("email@gmail.com")).isPresent();
    }

    @Test
    public void signIn() throws Exception {

        final AccountRequest request = new AccountRequest("name", "email@gmail.com", "44555555555", "1234", AccountType.PASSENGER);

        mockMvc.perform(post("/api/auth/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(request)));

        final AccountLoginRequest requestLogin = new AccountLoginRequest("email@gmail.com", "1234");

        String token = mockMvc.perform(post("/api/auth/sign-in")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(requestLogin)))
                .andReturn().getResponse().getContentAsString();

        AccountEntity accountEntity = accountRepository.findByEmail("email@gmail.com").get();

        Assertions.assertThat(JwtTokenValidator.isValidToken(token, accountEntity)).isTrue();
        Assertions.assertThat(JwtTokenValidator.getUsername(token)).isEqualTo("email@gmail.com");
    }

    @Test
    public void signInWithWrongPassword() throws Exception {
        final AccountRequest request = new AccountRequest("name", "email@gmail.com", "44555555555", "1234", AccountType.PASSENGER);

        mockMvc.perform(post("/api/auth/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(request)));

        final AccountLoginRequest requestLogin = new AccountLoginRequest("email@gmail.com", "5657");

        mockMvc.perform(post("/api/auth/sign-in")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(requestLogin)))
                .andExpect(status().is4xxClientError());
    }

}
