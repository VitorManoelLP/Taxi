package com.taxi.app.application.usecase;

import com.taxi.app.application.usecase.fare.MidnightFareCalculator;
import com.taxi.app.application.usecase.fare.NormalFareCalculator;
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
