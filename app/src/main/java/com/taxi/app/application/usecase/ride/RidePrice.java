package com.taxi.app.application.usecase.ride;

import java.util.UUID;

import com.taxi.app.application.usecase.AccountManager;
import com.taxi.app.application.usecase.distance.CalculateDistance;
import com.taxi.app.application.usecase.fare.FareCalculator;
import com.taxi.app.application.usecase.fare.HoursResolver;
import com.taxi.app.domain.Fare;
import com.taxi.app.domain.Location;
import com.taxi.app.domain.RequestedRides;
import com.taxi.app.dto.RidePriceResponse;

public class RidePrice {

    private final CalculateDistance calculateDistance;
    private final FareCalculator fareCalculator;
    private final RequestRide requestRide;
    private final AccountManager accountManager;

    public RidePrice(CalculateDistance calculateDistance, HoursResolver hoursResolver, RequestRide requestRide,
            AccountManager accountManager) {
        this.calculateDistance = calculateDistance;
        this.fareCalculator = FareCalculator.factory(hoursResolver);
        this.requestRide = requestRide;
        this.accountManager = accountManager;
    }

    public RidePriceResponse calculate(final String from, final String to) {
        final Location location = calculateDistance.calculate(from, to);
        final Fare fare = fareCalculator.calculate(location.distance());
        final UUID requestedId = requestRide.request(
                new RequestedRides(location.nameFrom(), location.nameTo(), from, to, fare.fare(), accountManager.getAccountByContext(), false));
        return new RidePriceResponse(requestedId, location.nameFrom(), location.nameTo(), fare.fare(),
                location.timeToArrive());
    }

}
