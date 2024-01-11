package com.taxi.app.infra.usecase.resolvers;

import com.taxi.app.application.usecase.ride.resolvers.RidePriceResolver;
import com.taxi.app.domain.Coord;
import com.taxi.app.domain.object.CepVO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GoogleRidePriceResolver implements RidePriceResolver {

    @Override
    public Coord resolve(CepVO cep) {
        return null;
    }

}
