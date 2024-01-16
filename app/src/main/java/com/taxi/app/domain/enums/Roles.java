package com.taxi.app.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Roles {

    USER(1L, "ROLE_USER", "USER"),
    ADMIN(2L, "ROLE_ADMIN", "ADMIN");

    private final Long id;
    private final String description;
    private final String role;

}
