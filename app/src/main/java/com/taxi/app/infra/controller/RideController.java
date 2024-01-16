package com.taxi.app.infra.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.taxi.app.application.usecase.ConfirmRide;
import com.taxi.app.application.usecase.ride.RidePrice;
import com.taxi.app.dto.RidePriceResponse;
import com.taxi.app.infra.usecase.producer.AcceptRideProducer;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/ride")
@RequiredArgsConstructor
public class RideController {

    private final RidePrice ridePrice;
    private final ConfirmRide confirmRide;
    private final AcceptRideProducer acceptRide;

    @GetMapping("/calculator")
    public ResponseEntity<RidePriceResponse> calculateFromTo(@RequestParam("from") String cepFrom, @RequestParam("to") String cepTo) {
        return ResponseEntity.ok(ridePrice.calculate(cepFrom, cepTo));
    }

    @PostMapping("/confirm/{requestRideId}")
    public ResponseEntity<Void> confirmRequestRide(@PathVariable UUID requestRideId) {
        confirmRide.confirm(requestRideId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/accept/{requestRideId}")
    public ResponseEntity<Void> acceptRide(@PathVariable UUID requestRideId) {
        acceptRide.accept(requestRideId);
        return ResponseEntity.ok().build();
    }

}
