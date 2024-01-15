package com.taxi.app.infra.usecase;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taxi.app.application.usecase.persistence.ExistsCoord;
import com.taxi.app.application.usecase.persistence.SaveCoord;
import com.taxi.app.domain.Coord;
import com.taxi.app.domain.Ride;
import com.taxi.app.infra.entity.mapper.RideMapper;
import com.taxi.app.infra.repository.RideRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class SaveRide implements com.taxi.app.application.usecase.persistence.SaveRide {

    private final RideRepository rideRepository;
    private final SaveCoord saveCoord;
    private final ExistsCoord existsCoord;

    @Override
    public void save(Ride ride) {
        verifyExistsCoord(ride);
        rideRepository.save(RideMapper.toEntity(ride));
    }

    private void verifyExistsCoord(Ride ride) {
        saveCoordBy(ride.from());
        saveCoordBy(ride.to());
    }

    private void saveCoordBy(Coord ride) {
        if (!existsCoord.exists(ride)) {
            saveCoord.save(ride);
        }
    }

}
