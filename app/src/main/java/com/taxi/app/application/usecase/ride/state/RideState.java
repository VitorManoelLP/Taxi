package com.taxi.app.application.usecase.ride.state;

import com.taxi.app.domain.Ride;
import com.taxi.app.domain.enums.RideStatus;

public interface RideState {

    static RideState of(RideStatus rideStatus) {

        if (rideStatus.equals(RideStatus.STARTED)) {
            return new RIdeStatusStarted();
        }

        if (rideStatus.equals(RideStatus.NOT_STARTED)) {
            return new RideStatusNotStarted();
        }

        if (rideStatus.equals(RideStatus.CANCELED)) {
            return new RideStatusCancel();
        }

        if (rideStatus.equals(RideStatus.COMPLETED)) {
            return new RideStatusCompleted();
        }

        throw new IllegalStateException("Ride status not identified");
    }

    RideStatus getRideStatus();

    void setNotStarted(Ride ride);

    void setStarted(Ride ride);

    void setCanceled(Ride ride);

    void setCompleted(Ride ride);

}
