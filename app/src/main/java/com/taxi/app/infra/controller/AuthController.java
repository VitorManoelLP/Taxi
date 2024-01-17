package com.taxi.app.infra.controller;

import java.net.URI;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.taxi.app.application.usecase.AccountManager;
import com.taxi.app.dto.AccountLoginRequest;
import com.taxi.app.dto.AccountRequest;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AccountManager accountManager;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/sign-up")
    public ResponseEntity<Void> signUp(@RequestBody @Valid final AccountRequest accountRequest) {
        accountManager.signUp(accountRequest);
        return ResponseEntity
                .created(URI.create(ServletUriComponentsBuilder.fromCurrentRequest().toUriString()))
                .build();
    }

    @ApiResponse(description = "Token JWT")
    @PostMapping(value = "/sign-in", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> signIn(@RequestBody @Valid final AccountLoginRequest accountRequest) {
        return ResponseEntity.ok(accountManager.signIn(accountRequest, authenticationManager));
    }

}
