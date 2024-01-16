package com.taxi.app.application.usecase;

import java.util.UUID;

public interface ConfirmRide {
    void confirm(UUID requestedRideId);
}
