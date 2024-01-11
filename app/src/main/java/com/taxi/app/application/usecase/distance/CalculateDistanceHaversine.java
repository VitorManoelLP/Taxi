package com.taxi.app.application.usecase.distance;

import com.taxi.app.domain.Coord;

public class CalculateDistanceHaversine implements CalculateDistance {

    private static final int EARTH_RADIUS = 6371;
    private static final int DEGREES = 180;

    @Override
    public double calculate(Coord from, Coord to) {

        final double degreesToRadians = Math.PI / DEGREES;

        double deltaLat = (to.latitude() - from.latitude()) * degreesToRadians;
        double deltaLon = (to.longitude() - from.longitude()) * degreesToRadians;

        double delta = Math.sin(deltaLat / 2.0) * Math.sin(deltaLat / 2.0) +
                Math.cos(from.latitude() * degreesToRadians) * Math.cos(to.latitude() * degreesToRadians) *
                        Math.sin(deltaLon / 2.0) * Math.sin(deltaLon / 2.0);

        double distance = 2.0 * Math.atan2(Math.sqrt(delta), Math.sqrt(1.0 - delta));

        return (int) Math.round(EARTH_RADIUS * distance);
    }


}
