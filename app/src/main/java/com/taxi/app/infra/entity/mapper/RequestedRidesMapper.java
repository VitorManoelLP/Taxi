package com.taxi.app.infra.entity.mapper;

import java.util.UUID;

import com.taxi.app.domain.RequestedRides;
import com.taxi.app.infra.entity.AccountEntity;
import com.taxi.app.infra.entity.RequestedRidesEntity;

import lombok.experimental.UtilityClass;

@UtilityClass
public class RequestedRidesMapper {

    public RequestedRidesEntity toEntity(RequestedRides requestedRides) {
        return new RequestedRidesEntity(
                UUID.randomUUID(),
                requestedRides.fromName(),
                requestedRides.toName(),
                requestedRides.cepFrom(),
                requestedRides.cepTo(),
                requestedRides.price(),
                AccountEntity.of(requestedRides.passengerId())
        );
    }

}
