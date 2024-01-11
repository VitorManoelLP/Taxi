package com.taxi.app.application.usecase.ride;

import java.math.BigDecimal;
import java.util.UUID;

import com.taxi.app.application.usecase.persistence.SaveRide;
import com.taxi.app.application.usecase.ride.state.RideStatusNotStarted;
import com.taxi.app.domain.Coord;
import com.taxi.app.domain.Ride;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StartRide {

    private final SaveRide saveRide;

    public void start(UUID idDriver, UUID idPassenger, BigDecimal price, Coord from, Coord to) {
        final Ride ride = new Ride(price, idDriver, idPassenger, new RideStatusNotStarted(), from, to);
        saveRide.save(ride);
    }

}
