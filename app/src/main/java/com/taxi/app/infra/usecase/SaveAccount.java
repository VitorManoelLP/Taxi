package com.taxi.app.infra.usecase;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taxi.app.application.usecase.SaveAccountUsecase;
import com.taxi.app.domain.Account;
import com.taxi.app.infra.entity.mapper.AccountMapper;
import com.taxi.app.infra.repository.AccountRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class SaveAccount implements SaveAccountUsecase {

    private final AccountRepository accountRepository;

    @Override
    public UserDetails save(Account account, String password) {
        return accountRepository.save(AccountMapper.toEntity(account, password));
    }

}
