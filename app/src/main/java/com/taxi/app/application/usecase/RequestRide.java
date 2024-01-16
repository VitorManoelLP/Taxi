package com.taxi.app.application.usecase;

import java.util.UUID;

import com.taxi.app.domain.RequestedRides;

public interface RequestRide {
    UUID request(RequestedRides requestedRides);
}
