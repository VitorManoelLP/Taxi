package com.taxi.app.application.usecase.ride.state;

import com.taxi.app.domain.Ride;
import com.taxi.app.domain.enums.RideStatus;

public class RideStatusNotStarted implements RideState {

    private RideStatus rideStatus = RideStatus.NOT_STARTED;

    @Override
    public RideStatus getRideStatus() {
        return rideStatus;
    }

    @Override
    public void setNotStarted(Ride ride) {
        rideStatus = RideStatus.NOT_STARTED;
    }

    @Override
    public void setStarted(Ride ride) {
        throw new IllegalStateException("Doesn't allow to started ride before exists!");
    }

    @Override
    public void setCanceled(Ride ride) {
        throw new IllegalStateException("Doesn't allow to cancel ride before exists");
    }

    @Override
    public void setCompleted(Ride ride) {
        throw new IllegalStateException("Doesn't allow to complete ride before exists");
    }

}
