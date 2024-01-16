package com.taxi.app.application.usecase.distanceCalculator;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.taxi.app.application.usecase.CalculateDistance;
import com.taxi.app.domain.Coord;
import com.taxi.app.domain.Location;
import com.taxi.app.domain.object.CepVO;
import com.taxi.app.infra.usecase.RideCoordResolver;
import com.taxi.app.infra.usecase.distance.CalculateDistanceHaversine;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CalculateDistanceTest {

    @Mock
    private RideCoordResolver rideCoordResolver;

    @BeforeEach
    public void setup() {
        when(rideCoordResolver.resolve(new CepVO("87023060")))
                .thenReturn(new Coord("87023060", "Rua Foo", -25.4284, -49.2733, ""));

        when(rideCoordResolver.resolve(new CepVO("87035350")))
                .thenReturn(new Coord("1", "Rua Foo2", -23.4273, -51.9375, ""));
    }

    @Test
    public void calculateDistanceWithHaversineMethod() {
        final CalculateDistance calculateDistance = new CalculateDistanceHaversine(rideCoordResolver);
        final Location distance = calculateDistance.calculate("87023060", "87035350");
        Assertions.assertThat(distance.distance()).isEqualTo(349642.0);
    }

}
