package com.taxi.app.infra.usecase;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.taxi.app.application.usecase.AccountManager;
import com.taxi.app.application.usecase.ride.StartRide;
import com.taxi.app.infra.repository.RequestedRidesRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AcceptRide implements com.taxi.app.application.usecase.AcceptRide {

    private final RequestedRidesRepository requestedRidesRepository;
    private final StartRide startRide;
    private final AccountManager accountManager;

    @Override
    public void accept(UUID requestId, String requestedBy) {
        requestedRidesRepository.findById(requestId).ifPresent(request -> {
            final UUID idDriver = accountManager.getAccountByEmail(requestedBy);
            request.accept();
            startRide.start(idDriver, request.getPassenger().getId(), request.getPrice(), request.getCepFrom(), request.getCepTo());
        });
    }

}
