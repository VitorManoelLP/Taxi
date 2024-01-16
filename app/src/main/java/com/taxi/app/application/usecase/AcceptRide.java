package com.taxi.app.application.usecase;

import java.util.UUID;

public interface AcceptRide {
    void accept(UUID requestId, String requestedBy);
}
