package com.taxi.app.dto;

import java.math.BigDecimal;

public record RidePriceResponse(String fromName, String toName, BigDecimal price, String timeToArrive) {
}
