package com.taxi.app.infra.usecase;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.taxi.app.domain.RequestedRides;
import com.taxi.app.infra.entity.RequestedRidesEntity;
import com.taxi.app.infra.entity.mapper.RequestedRidesMapper;
import com.taxi.app.infra.repository.RequestedRidesRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RequestRide implements com.taxi.app.application.usecase.ride.RequestRide {

    private final RequestedRidesRepository requestedRidesRepository;

    @Override
    public UUID request(RequestedRides requestedRides) {
        final RequestedRidesEntity entity = RequestedRidesMapper.toEntity(requestedRides);
        requestedRidesRepository.save(entity);
        return entity.getId();
    }

}
