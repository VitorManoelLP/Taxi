package com.taxi.app.infra.entity.mapper;

import java.util.UUID;

import com.taxi.app.domain.Account;
import com.taxi.app.domain.object.EmailVO;
import com.taxi.app.domain.object.PhoneVO;
import com.taxi.app.dto.AccountRequest;
import com.taxi.app.infra.entity.AccountEntity;

import lombok.experimental.UtilityClass;

@UtilityClass
public class AccountMapper {

    public AccountEntity toEntity(Account account, String password) {

        final AccountEntity accountEntity = new AccountEntity(UUID.randomUUID(), null, account.name(),
                account.phone().toString(), account.email().getValue(), password, account.accountType());

        accountEntity.addAuthorityBase();

        return accountEntity;
    }

    public Account toDomain(AccountEntity account) {
        return new Account(account.getName(), null, PhoneVO.of(account.getPhone()), new EmailVO(account.getEmail()), account.getAccountType());
    }

    public Account toDomain(AccountRequest accountRequest) {
        return new Account(accountRequest.name(), null, PhoneVO.of(accountRequest.phone()), new EmailVO(accountRequest.email()),
                accountRequest.accountType());
    }

}
