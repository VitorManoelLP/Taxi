package com.taxi.app.application.usecase.distance;

import com.taxi.app.domain.Coord;

public interface GeolocationCalculator {

    Coord calculate(String cep);
}
