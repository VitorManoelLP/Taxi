package com.taxi.app.infra.usecase;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taxi.app.domain.Ride;
import com.taxi.app.infra.entity.mapper.RideMapper;
import com.taxi.app.infra.repository.RideRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class SaveRide implements com.taxi.app.application.usecase.SaveRide {

    private final RideRepository rideRepository;

    @Override
    public void save(Ride ride) {
        rideRepository.save(RideMapper.toEntity(ride));
    }

}
