package com.taxi.app.infra.usecase;

import java.util.UUID;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.taxi.app.infra.repository.RequestedRidesRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ConfirmRide implements com.taxi.app.application.usecase.ConfirmRide {

    private final SimpMessagingTemplate template;
    private final RequestedRidesRepository requestedRidesRepository;

    @Override
    public void confirm(UUID requestedRideId) {
        requestedRidesRepository.findById(requestedRideId).ifPresent(request -> template.convertAndSend("all-drivers-account", request));
    }

}
