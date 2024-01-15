package com.taxi.app.infra.usecase;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.taxi.app.application.usecase.persistence.SaveAccountUsecase;
import com.taxi.app.domain.Account;
import com.taxi.app.domain.enums.AccountType;
import com.taxi.app.domain.object.EmailVO;
import com.taxi.app.domain.object.PhoneVO;
import com.taxi.app.extension.ContainerBaseExtension;
import com.taxi.app.infra.entity.AccountEntity;
import com.taxi.app.infra.repository.AccountRepository;

public class SaveAccountTest extends ContainerBaseExtension {

    @Autowired
    private SaveAccountUsecase saveAccountUsecase;

    @Autowired
    private AccountRepository accountRepository;

    @AfterEach
    public void afterEach() {
        accountRepository.deleteAll();
    }

    @Test
    public void shouldSave() {

        saveAccountUsecase.save(
                new Account("test", null, new PhoneVO(44, 555544442, 55), new EmailVO("teste@gmail.com"), AccountType.DRIVER));

        final AccountEntity accountSaved = getEm().createQuery(
                        "SELECT entity FROM AccountEntity entity WHERE entity.email = 'teste@gmail.com'", AccountEntity.class)
                .getSingleResult();

        Assertions.assertThat(accountSaved).isNotNull();

    }

}
