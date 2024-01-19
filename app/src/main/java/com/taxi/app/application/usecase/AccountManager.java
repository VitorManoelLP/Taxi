package com.taxi.app.application.usecase;

import java.util.UUID;

import org.springframework.security.authentication.AuthenticationManager;

import com.taxi.app.dto.AccountLoginRequest;
import com.taxi.app.dto.AccountRequest;

public interface AccountManager {
    UUID getAccountByContext();
    UUID getAccountByEmail(String email);
    void signUp(AccountRequest accountRequest);
    String signIn(AccountLoginRequest accountRequest, AuthenticationManager authenticationManager);
}
