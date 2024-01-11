package com.taxi.app.application.usecase.ride;

import java.math.BigDecimal;

import com.taxi.app.application.usecase.ride.resolvers.RidePriceResolver;
import com.taxi.app.domain.Coord;
import com.taxi.app.domain.object.CepVO;
import com.taxi.app.dto.RidePriceResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RidePriceCalculator {

    private final RidePrice ridePrice;
    private final RidePriceResolver ridePriceResolver;

    public RidePriceResponse calculate(String from, String to) {
        final Coord resolvedCoordFrom = ridePriceResolver.resolve(new CepVO(from));
        final Coord resolvedCoordTo = ridePriceResolver.resolve(new CepVO(to));
        final BigDecimal price = ridePrice.calculate(resolvedCoordFrom, resolvedCoordTo);
        return new RidePriceResponse(resolvedCoordFrom.coordName(), resolvedCoordTo.coordName(), price);
    }

}
