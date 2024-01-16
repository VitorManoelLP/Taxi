package com.taxi.app.application.usecase;

import org.springframework.security.core.userdetails.UserDetails;

import com.taxi.app.domain.Account;

public interface SaveAccountUsecase {
    UserDetails save(Account account, String password);
}
