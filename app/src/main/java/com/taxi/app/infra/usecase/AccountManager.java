package com.taxi.app.infra.usecase;

import java.util.UUID;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taxi.app.application.usecase.SaveAccountUsecase;
import com.taxi.app.domain.object.EmailVO;
import com.taxi.app.dto.AccountLoginRequest;
import com.taxi.app.dto.AccountRequest;
import com.taxi.app.infra.config.security.AuthenticationConfiguration;
import com.taxi.app.infra.config.security.token.JwtTokenValidator;
import com.taxi.app.infra.entity.AccountEntity;
import com.taxi.app.infra.entity.mapper.AccountMapper;
import com.taxi.app.infra.repository.AccountRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountManager implements UserDetailsService, com.taxi.app.application.usecase.AccountManager {

    private final AccountRepository accountRepository;
    private final SaveAccountUsecase saveAccountUsecase;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return accountRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("User not found by email"));
    }

    @Override
    public UUID getAccountByContext() {
        return getAccountByEmail(String.valueOf(SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
    }

    @Override
    public UUID getAccountByEmail(String email) {
        return ((AccountEntity) loadUserByUsername(email)).getId();
    }

    @Override
    public void signUp(AccountRequest accountRequest) {
        saveAccountUsecase.save(AccountMapper.toDomain(accountRequest),
                AuthenticationConfiguration.password().encode(accountRequest.password()));
    }

    @Override
    public String signIn(AccountLoginRequest accountRequest, AuthenticationManager authenticationManager) {
        final EmailVO email = new EmailVO(accountRequest.email());
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email.getValue(), accountRequest.password()));
        final UserDetails userDetails = loadUserByUsername(email.getValue());
        return JwtTokenValidator.generateToken(userDetails);
    }

}
