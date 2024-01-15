package com.taxi.app.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record RidePriceResponse(UUID requestRide, String fromName, String toName, BigDecimal price, String timeToArrive) {
}
