package com.taxi.app.application.usecase;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.taxi.app.application.usecase.fare.FareCalculator;
import com.taxi.app.application.usecase.fare.MidnightFareCalculator;
import com.taxi.app.application.usecase.fare.NormalFareCalculator;
import com.taxi.app.domain.Fare;

@ExtendWith(MockitoExtension.class)
public class FareCalculatorTest {

    @Test
    public void calculateNormalFare() {
        final FareCalculator fareCalculator = new NormalFareCalculator();
        final double distance = 20000;
        final Fare fare = fareCalculator.calculate(distance);
        Assertions.assertEquals(fare.fare(), new BigDecimal("42.00"));
    }

    @Test
    public void calculateMidnightFare() {
        final FareCalculator fareCalculator = new MidnightFareCalculator();
        final double distance = 20000;
        final Fare fare = fareCalculator.calculate(distance);
        Assertions.assertEquals(fare.fare(), new BigDecimal("100.00"));
    }

}
