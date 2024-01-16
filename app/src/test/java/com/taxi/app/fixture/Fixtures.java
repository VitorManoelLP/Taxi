package com.taxi.app.fixture;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;

import com.taxi.app.domain.Account;
import com.taxi.app.domain.enums.AccountType;
import com.taxi.app.domain.enums.Roles;
import com.taxi.app.domain.object.EmailVO;
import com.taxi.app.domain.object.PhoneVO;

public final class Fixtures {

    private Fixtures() {}

    public static void setSecurityContext(String user) {
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(user, "dummy",
                        AuthorityUtils.createAuthorityList(Roles.USER.getDescription())));
    }

    public static void clearAll() {
        SecurityContextHolder.getContext().setAuthentication(null);
    }

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
