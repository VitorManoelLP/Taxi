package com.taxi.app.infra.entity.mapper;

import java.util.UUID;

import com.taxi.app.application.usecase.ride.state.RideState;
import com.taxi.app.domain.Ride;
import com.taxi.app.infra.entity.AccountEntity;
import com.taxi.app.infra.entity.CoordEntity;
import com.taxi.app.infra.entity.RideEntity;

import lombok.experimental.UtilityClass;

@UtilityClass
public class RideMapper {

    public Ride toDomain(RideEntity rideEntity) {
        return new Ride(rideEntity.getPrice(), rideEntity.getDriver().getId(), rideEntity.getPassenger().getId(),
                RideState.of(rideEntity.getRideStatus()), CoordMapper.toDomain(rideEntity.getFrom()), CoordMapper.toDomain(rideEntity.getTo()));
    }

    public RideEntity toEntity(Ride ride) {
        return new RideEntity(UUID.randomUUID(),
                ride.price(),
                AccountEntity.of(ride.idDriver()),
                AccountEntity.of(ride.idPassenger()),
                ride.rideState().getRideStatus(),
                CoordEntity.of(ride.from().latitude(), ride.from().longitude()),
                CoordEntity.of(ride.to().latitude(), ride.to().longitude()));
    }

}
