package com.taxi.app.application.usecase.ride;

import java.util.UUID;

public interface ConfirmRide {
    void confirm(UUID requestedRideId);
}
