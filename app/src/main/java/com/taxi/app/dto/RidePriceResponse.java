package com.taxi.app.dto;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.NonNull;

public record RidePriceResponse(@NonNull UUID requestRide, @NonNull String fromName, @NonNull String toName, @NonNull BigDecimal price, @NonNull String timeToArrive) {
}
