package com.taxi.app.dto;

import java.util.UUID;

import lombok.NonNull;

public record OutBoxResponse(@NonNull UUID idOutbox, @NonNull String requestedBy, boolean sent, @NonNull String exchange, String errorMessage) {
}
