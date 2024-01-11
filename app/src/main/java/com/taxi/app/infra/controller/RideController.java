package com.taxi.app.infra.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.taxi.app.application.usecase.ride.RidePriceCalculator;
import com.taxi.app.dto.RidePriceResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/ride")
@RequiredArgsConstructor
public class RideController {

    private final RidePriceCalculator ridePriceCalculator;

    @GetMapping("/calculator")
    public ResponseEntity<RidePriceResponse> calculateFromTo(@RequestParam("from") String cepFrom, @RequestParam("to") String cepTo) {
        return ResponseEntity.ok(ridePriceCalculator.calculate(cepFrom, cepTo));
    }

}
