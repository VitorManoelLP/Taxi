package com.taxi.app.application.usecase.fare;

import com.taxi.app.domain.Fare;

import java.math.BigDecimal;

public class MidnightFareCalculator implements FareCalculator {

    private static final double MIDNIGHT_FARE = 5;

    @Override
    public Fare calculate(double distance) {
        return new Fare(new BigDecimal(distance * MIDNIGHT_FARE));
    }
}
