package com.taxi.app.application.usecase.ride;

import java.math.BigDecimal;

import com.taxi.app.application.usecase.distance.CalculateDistance;
import com.taxi.app.application.usecase.fare.FareCalculator;
import com.taxi.app.application.usecase.fare.HoursResolver;
import com.taxi.app.domain.Coord;
import com.taxi.app.domain.Fare;

public class RidePrice {

    private final CalculateDistance calculateDistance;
    private final FareCalculator fareCalculator;

    public RidePrice(CalculateDistance calculateDistance, HoursResolver hoursResolver) {
        this.calculateDistance = calculateDistance;
        this.fareCalculator = FareCalculator.factory(hoursResolver);
    }

    public BigDecimal calculate(final Coord from, final Coord to) {
        final double distance = calculateDistance.calculate(from, to);
        final Fare fare = fareCalculator.calculate(distance);
        return fare.fare();
    }

}
