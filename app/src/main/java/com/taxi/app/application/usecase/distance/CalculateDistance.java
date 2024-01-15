package com.taxi.app.application.usecase.distance;

import com.taxi.app.domain.Location;

public interface CalculateDistance {
    Location calculate(String from, String  to);
}
