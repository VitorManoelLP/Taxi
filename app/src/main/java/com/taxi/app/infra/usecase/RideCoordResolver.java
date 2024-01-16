package com.taxi.app.infra.usecase;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taxi.app.application.usecase.GeolocationCalculator;
import com.taxi.app.application.usecase.SaveCoord;
import com.taxi.app.domain.Coord;
import com.taxi.app.domain.object.CepVO;
import com.taxi.app.infra.repository.CoordRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class RideCoordResolver {

    private final GeolocationCalculator geolocationCalculator;
    private final CoordRepository coordRepository;
    private final SaveCoord saveCoord;

    public Coord resolve(CepVO cep) {
        return coordRepository.findByCep(cep.getValue()).orElseGet(() -> {
            Coord coordCalculated = geolocationCalculator.calculate(cep.getValue());
            saveCoord.save(coordCalculated);
            return coordCalculated;
        });
    }

}
