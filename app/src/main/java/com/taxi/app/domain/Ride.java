package com.taxi.app.domain;

import com.taxi.app.application.usecase.ride.state.RideState;

import java.math.BigDecimal;
import java.util.UUID;

public record Ride(BigDecimal price, UUID idDriver, UUID idPassenger, RideState rideState, Coord from, Coord to) {
}
