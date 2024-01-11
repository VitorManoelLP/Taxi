package com.taxi.app.application.usecase.fare;

import com.taxi.app.domain.Fare;

public interface FareCalculator {

    static FareCalculator factory(HoursResolver hoursResolver) {

        if (hoursResolver.isMidnight()) {
            return new MidnightFareCalculator();
        }

        return new NormalFareCalculator();
    }

    Fare calculate(double distance);
}
