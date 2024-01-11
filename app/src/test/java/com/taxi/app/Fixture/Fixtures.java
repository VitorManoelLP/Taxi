package com.taxi.app.Fixture;

import com.taxi.app.domain.Account;
import com.taxi.app.domain.enums.AccountType;
import com.taxi.app.domain.object.EmailVO;
import com.taxi.app.domain.object.PhoneVO;

public final class Fixtures {

    private Fixtures() {}

    public static Account createDriver() {
        return createAccount(AccountType.DRIVER, "driver@gmail.com");
    }

    public static Account createPassanger() {
        return createAccount(AccountType.PASSENGER, "passenger@gmail.com");
    }

    public static Account createAccount(final AccountType accountType, final String email) {
        return new Account("name-fake", null, PhoneVO.of("5544555544444"), new EmailVO(email), accountType);
    }

}
