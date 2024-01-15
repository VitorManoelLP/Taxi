package com.taxi.app.application.usecase.ride.resolvers;

import com.taxi.app.dto.RidePriceResponse;

public interface RidePriceCalculator {

    RidePriceResponse calculate(String from, String to);

}
