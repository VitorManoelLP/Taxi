package com.taxi.app.infra.usecase;

import org.springframework.stereotype.Service;

import com.taxi.app.application.usecase.persistence.SaveAccountUsecase;
import com.taxi.app.domain.Account;
import com.taxi.app.infra.entity.mapper.AccountMapper;
import com.taxi.app.infra.repository.AccountRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SaveAccount implements SaveAccountUsecase {

    private final AccountRepository accountRepository;

    @Override
    public void save(Account account) {
        accountRepository.save(AccountMapper.toEntity(account));
    }

}
