package com.taxi.app.application.usecase.ride.state;

import com.taxi.app.domain.Ride;
import com.taxi.app.domain.enums.RideStatus;

public class RideStatusCancel implements RideState {

    private RideStatus rideStatus = RideStatus.CANCELED;

    @Override
    public RideStatus getRideStatus() {
        return rideStatus;
    }

    @Override
    public void setNotStarted(Ride ride) {
        throw new IllegalStateException("Doesn't allow wait ride it already canceled");
    }

    @Override
    public void setStarted(Ride ride) {
        throw new IllegalStateException("Doesn't allow start ride it already canceled");
    }

    @Override
    public void setCanceled(Ride ride) {
        rideStatus = RideStatus.CANCELED;
    }

    @Override
    public void setCompleted(Ride ride) {
        throw new IllegalStateException("Doesn't allow complete ride it already canceled");

    }
}
