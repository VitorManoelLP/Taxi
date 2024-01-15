package com.taxi.app.infra.entity.mapper;

import com.taxi.app.domain.Coord;
import com.taxi.app.infra.entity.CoordEntity;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CoordMapper {

    public Coord toDomain(CoordEntity coordEntity) {
        return new Coord(coordEntity.getCep(), coordEntity.getCoordName(), coordEntity.getLatitude(), coordEntity.getLongitude(), coordEntity.getPlaceId());
    }

    public CoordEntity toEntity(Coord coord) {
        return new CoordEntity(coord.latitude(), coord.longitude(), coord.coordName(), coord.cep(), coord.placeId());
    }

}
