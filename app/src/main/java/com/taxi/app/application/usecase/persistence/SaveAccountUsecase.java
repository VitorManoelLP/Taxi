package com.taxi.app.application.usecase.persistence;

import com.taxi.app.domain.Account;

public interface SaveAccountUsecase {
    void save(Account account);
}
