package com.taxi.app.application.usecase.distanceCalculator;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import com.taxi.app.application.usecase.distance.CalculateDistance;
import com.taxi.app.application.usecase.distance.CalculateDistanceHaversine;
import com.taxi.app.domain.Coord;

public class CalculateDistanceTest {

    @Test
    public void calculateDistanceWithHaversineMethod() {
        final CalculateDistance calculateDistance = new CalculateDistanceHaversine();
        final double distance = calculateDistance.calculate(new Coord("1", "Rua Foo", -25.4284, -49.2733), new Coord("1", "Rua Foo2", -23.4273, -51.9375));
        Assertions.assertThat(distance).isEqualTo(350);
    }

    @Test
    public void calculateDistanceWithHaversineMethodMoreDistance() {
        final CalculateDistance calculateDistance = new CalculateDistanceHaversine();
        final double distance = calculateDistance.calculate(new Coord("1", "Rua Foo", -23.4581, -52.0494), new Coord("1", "Rua Foo2", 35.0210700, 135.7538500));
        Assertions.assertThat(distance).isEqualTo(18524);
    }

}
