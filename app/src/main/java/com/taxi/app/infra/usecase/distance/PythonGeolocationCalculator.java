package com.taxi.app.infra.usecase.distance;

import com.taxi.app.application.usecase.distance.GeolocationCalculator;
import com.taxi.app.domain.Coord;
import com.taxi.app.infra.clients.GeolocationPythonClient;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PythonGeolocationCalculator implements GeolocationCalculator {

    private final GeolocationPythonClient geolocationPythonClient;

    @Override
    public Coord calculate(String cep) {
        return geolocationPythonClient.calculate(cep);
    }

}
