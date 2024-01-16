package com.taxi.app.application.usecase.ride;

import java.math.BigDecimal;
import java.util.UUID;

import com.taxi.app.application.usecase.SaveRide;
import com.taxi.app.application.usecase.ride.state.RideStatusNotStarted;
import com.taxi.app.domain.Coord;
import com.taxi.app.domain.Ride;
import com.taxi.app.infra.repository.CoordRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StartRide {

    private final SaveRide saveRide;
    private final CoordRepository coordRepository;

    public void start(UUID idDriver, UUID idPassenger, BigDecimal price, String from, String to) {
        Coord fromCoord = coordRepository.findByCep(from).orElseThrow(() -> new IllegalStateException("Coord not found"));
        Coord toCord = coordRepository.findByCep(to).orElseThrow(() -> new IllegalStateException("Coord not found"));
        final Ride ride = new Ride(price, idDriver, idPassenger, new RideStatusNotStarted(), fromCoord, toCord);
        saveRide.save(ride);
    }

}
