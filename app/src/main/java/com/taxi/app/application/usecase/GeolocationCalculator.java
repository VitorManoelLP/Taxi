package com.taxi.app.application.usecase;

import com.taxi.app.domain.Coord;

public interface GeolocationCalculator {

    Coord calculate(String cep);
}
