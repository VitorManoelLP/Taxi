package com.taxi.app.application.usecase.ride.state;

import com.taxi.app.domain.Ride;
import com.taxi.app.domain.enums.RideStatus;

public class RideStatusCompleted implements RideState {

    private RideStatus rideStatus = RideStatus.COMPLETED;

    @Override
    public RideStatus getRideStatus() {
        return rideStatus;
    }

    @Override
    public void setNotStarted(Ride ride) {
        throw new IllegalStateException("Doesn't allow wait ride it already completed");
    }

    @Override
    public void setStarted(Ride ride) {
        throw new IllegalStateException("Doesn't allow wait ride it already started");
    }

    @Override
    public void setCanceled(Ride ride) {
        throw new IllegalStateException("Doesn't allow wait ride it already canceled");
    }

    @Override
    public void setCompleted(Ride ride) {
        rideStatus = RideStatus.COMPLETED;
    }

}
