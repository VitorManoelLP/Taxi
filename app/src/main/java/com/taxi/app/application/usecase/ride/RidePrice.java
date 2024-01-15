package com.taxi.app.application.usecase.ride;

import com.taxi.app.application.usecase.distance.CalculateDistance;
import com.taxi.app.application.usecase.fare.FareCalculator;
import com.taxi.app.application.usecase.fare.HoursResolver;
import com.taxi.app.domain.Fare;
import com.taxi.app.domain.Location;
import com.taxi.app.dto.RidePriceResponse;

public class RidePrice {

    private final CalculateDistance calculateDistance;
    private final FareCalculator fareCalculator;

    public RidePrice(CalculateDistance calculateDistance, HoursResolver hoursResolver) {
        this.calculateDistance = calculateDistance;
        this.fareCalculator = FareCalculator.factory(hoursResolver);
    }

    public RidePriceResponse calculate(final String from, final String to) {
        final Location location = calculateDistance.calculate(from, to);
        final Fare fare = fareCalculator.calculate(location.distance());
        return new RidePriceResponse(location.nameFrom(), location.nameTo(), fare.fare(), location.timeToArrive());
    }

}
