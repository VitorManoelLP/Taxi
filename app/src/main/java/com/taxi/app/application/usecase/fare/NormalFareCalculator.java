package com.taxi.app.application.usecase.fare;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.taxi.app.domain.Fare;

public class NormalFareCalculator implements FareCalculator {

    private static final double NORMAL_FARE = 2.1;

    @Override
    public Fare calculate(double distance) {
        return new Fare(new BigDecimal(distance * NORMAL_FARE).setScale(2, RoundingMode.HALF_EVEN));
    }
}
