package com.taxi.app.application.usecase.distance;

import com.taxi.app.domain.Coord;

public interface CalculateDistance {
    double calculate(Coord from, Coord to);
}
