package com.taxi.app.infra.usecase;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taxi.app.domain.Coord;
import com.taxi.app.infra.repository.CoordRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ExistsCoord implements com.taxi.app.application.usecase.persistence.ExistsCoord {

    private final CoordRepository coordRepository;

    @Override
    public boolean exists(Coord coord) {
        return coordRepository.existsByLatitudeAndLongitude(coord.latitude(), coord.longitude());
    }

}
