package com.taxi.app.infra.entity.mapper;

import java.util.UUID;

import com.taxi.app.domain.Account;
import com.taxi.app.domain.object.EmailVO;
import com.taxi.app.domain.object.PhoneVO;
import com.taxi.app.infra.entity.AccountEntity;

import lombok.experimental.UtilityClass;

@UtilityClass
public class AccountMapper {

    public AccountEntity toEntity(Account account) {
        return new AccountEntity(UUID.randomUUID(), null, account.name(), account.phone().toString(), account.email().getValue(), account.accountType());
    }

    public Account toDomain(AccountEntity account) {
        return new Account(account.getName(), null, PhoneVO.of(account.getPhone()), new EmailVO(account.getEmail()), account.getAccountType());
    }

}
