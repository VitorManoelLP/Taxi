package com.taxi.app.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.taxi.app.domain.enums.AccountType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record AccountRequest(@NotBlank String name, @NotBlank String email, @NotBlank String phone, @NotBlank String password, @NotNull AccountType accountType) {
}
