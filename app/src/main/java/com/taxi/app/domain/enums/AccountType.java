package com.taxi.app.domain.enums;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AccountType {

    DRIVER(1L, "Driver"),
    PASSENGER(2L, "Passenger");

    private final long id;
    private final String description;

    @JsonValue
    public long getId() {
        return id;
    }

    @JsonCreator
    public static AccountType getType(long typeId) {
        List<AccountType> accountTypes = List.of(AccountType.DRIVER, AccountType.PASSENGER);
        return accountTypes.stream().filter(type -> type.getId() == typeId).findFirst().orElse(null);
    }

    public boolean isDriver() {
        return this.equals(DRIVER);
    }

    public boolean isPassenger() {
        return this.equals(PASSENGER);
    }
}
