package com.taxi.app.application.usecase.ride.resolvers;

import com.taxi.app.domain.Coord;
import com.taxi.app.domain.object.CepVO;

public interface RidePriceResolver {
    Coord resolve(CepVO cep);
}
