package com.taxi.app.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import jakarta.validation.constraints.NotBlank;


@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record AccountLoginRequest(@NotBlank String email,@NotBlank String password) {
}
