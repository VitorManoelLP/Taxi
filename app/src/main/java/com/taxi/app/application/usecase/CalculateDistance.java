package com.taxi.app.application.usecase;

import com.taxi.app.domain.Location;

public interface CalculateDistance {
    Location calculate(String from, String  to);
}
