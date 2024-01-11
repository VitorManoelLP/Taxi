package com.taxi.app.infra.usecase.resolvers;

import com.taxi.app.application.usecase.persistence.SaveCoord;
import com.taxi.app.application.usecase.ride.resolvers.RidePriceResolver;
import com.taxi.app.domain.Coord;
import com.taxi.app.domain.object.CepVO;
import com.taxi.app.infra.clients.GeolocationPythonClient;
import com.taxi.app.infra.repository.CoordRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PythonRidePriceResolver implements RidePriceResolver {

    private final GeolocationPythonClient geolocationPythonClient;
    private final CoordRepository coordRepository;
    private final SaveCoord saveCoord;

    @Override
    public Coord resolve(CepVO cep) {
        return coordRepository.findByCep(cep.getValue()).orElseGet(() -> {
            Coord coordCalculated = geolocationPythonClient.calculate(cep.getValue());
            saveCoord.save(coordCalculated);
            return coordCalculated;
        });
    }

}
