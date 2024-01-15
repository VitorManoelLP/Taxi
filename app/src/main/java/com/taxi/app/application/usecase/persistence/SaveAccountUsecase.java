package com.taxi.app.application.usecase.persistence;

import org.springframework.security.core.userdetails.UserDetails;

import com.taxi.app.domain.Account;

public interface SaveAccountUsecase {
    UserDetails save(Account account, String password);
}
