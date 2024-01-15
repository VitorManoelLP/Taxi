package com.taxi.app.infra.usecase.distance;

import com.taxi.app.application.usecase.distance.CalculateDistance;
import com.taxi.app.domain.Coord;
import com.taxi.app.domain.Location;
import com.taxi.app.domain.object.CepVO;
import com.taxi.app.infra.usecase.RideCoordResolver;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CalculateDistanceHaversine implements CalculateDistance {

    private static final int EARTH_RADIUS = 6371000;
    private static final int DEGREES = 180;

    private final RideCoordResolver rideCoordResolver;

    @Override
    public Location calculate(String from, String to) {
        Coord fromCoord = rideCoordResolver.resolve(new CepVO(from));
        Coord toCoord = rideCoordResolver.resolve(new CepVO(to));
        double delta = getDelta(toCoord, fromCoord);
        final long distance = Math.round(EARTH_RADIUS * 2.0 * Math.atan2(Math.sqrt(delta), Math.sqrt(1.0 - delta)));
        return new Location(fromCoord.coordName(), toCoord.coordName(), distance, "not calculated");
    }

    private  double getDelta(Coord toCoord, Coord fromCoord) {
        final double degreesToRadians = Math.PI / DEGREES;
        double deltaLat = (toCoord.latitude() - fromCoord.latitude()) * degreesToRadians;
        double deltaLon = (toCoord.longitude() - fromCoord.longitude()) * degreesToRadians;
        return Math.sin(deltaLat / 2.0) * Math.sin(deltaLat / 2.0) +
                Math.cos(fromCoord.latitude() * degreesToRadians) * Math.cos(toCoord.latitude() * degreesToRadians) *
                        Math.sin(deltaLon / 2.0) * Math.sin(deltaLon / 2.0);
    }

}
