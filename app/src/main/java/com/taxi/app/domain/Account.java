package com.taxi.app.domain;

import com.taxi.app.domain.enums.AccountType;
import com.taxi.app.domain.object.EmailVO;
import com.taxi.app.domain.object.PhoneVO;

public record Account(String name, byte[] image, PhoneVO phone, EmailVO email, AccountType accountType) {
}
