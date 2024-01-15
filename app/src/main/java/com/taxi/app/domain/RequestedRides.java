package com.taxi.app.domain;

import java.math.BigDecimal;
import java.util.UUID;

public record RequestedRides(String fromName, String toName, String cepFrom, String cepTo, BigDecimal price, UUID passengerId) {
}
