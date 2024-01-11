package com.taxi.app.application.usecase.ride.state;

import com.taxi.app.domain.Ride;
import com.taxi.app.domain.enums.RideStatus;

public class RIdeStatusStarted implements RideState {

    private RideStatus rideStatus = RideStatus.STARTED;

    @Override
    public RideStatus getRideStatus() {
        return rideStatus;
    }

    @Override
    public void setNotStarted(Ride ride) {
        throw new IllegalStateException("Doesn't allow to wait ride when it already started!");
    }

    @Override
    public void setStarted(Ride ride) {
        rideStatus = RideStatus.STARTED;
    }

    @Override
    public void setCanceled(Ride ride) {
        throw new IllegalStateException("Doesn't allow to cancel ride when it already started!");
    }

    @Override
    public void setCompleted(Ride ride) {
        throw new IllegalStateException("Doesn't allow to complete ride when it already started!");
    }
}
