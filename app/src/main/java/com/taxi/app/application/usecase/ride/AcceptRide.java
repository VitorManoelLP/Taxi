package com.taxi.app.application.usecase.ride;

import java.util.UUID;

public interface AcceptRide {
    void accept(UUID requestId, String requestedBy);
}
