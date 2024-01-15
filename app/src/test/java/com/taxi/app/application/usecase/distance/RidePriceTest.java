package com.taxi.app.application.usecase.distance;

import java.math.BigDecimal;

import org.assertj.core.api.Assertions;
import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.taxi.app.application.usecase.fare.HoursResolver;
import com.taxi.app.application.usecase.ride.RidePrice;
import com.taxi.app.domain.Coord;
import com.taxi.app.domain.object.CepVO;
import com.taxi.app.dto.RidePriceResponse;
import com.taxi.app.infra.usecase.RequestRide;
import com.taxi.app.infra.usecase.RideCoordResolver;
import com.taxi.app.infra.usecase.distance.CalculateDistanceHaversine;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RidePriceTest {

    @Mock
    private RideCoordResolver rideCoordResolver;

    @Mock
    private RequestRide requestRide;

    @Test
    public void calculate() {

        when(rideCoordResolver.resolve(new CepVO("87023060")))
                .thenReturn(new Coord("87023060", "Rua Foo", -25.4284, -49.2733, ""));

        when(rideCoordResolver.resolve(new CepVO("87035350")))
                .thenReturn(new Coord("87035350", "Rua Foo2", -23.4273, -51.9375, ""));

        final RidePrice ridePrice = new RidePrice(
                new CalculateDistanceHaversine(rideCoordResolver),
                new HoursResolverFake(),
                requestRide
        );

        final RidePriceResponse price = ridePrice.calculate("87023060", "87035350");

        Assertions.assertThat(price.price()).isCloseTo(new BigDecimal("734.25"), Offset.offset(new BigDecimal("0.1")));
    }

    @Test
    public void calculate2() {

        when(rideCoordResolver.resolve(new CepVO("87023060")))
                .thenReturn(new Coord("87023060", "Rua Foo", -23.3965, -51.9346, ""));

        when(rideCoordResolver.resolve(new CepVO("87035350")))
                .thenReturn(new Coord("1", "Rua Foo2", -23.3885, -51.8967, ""));

        final RidePrice ridePrice = new RidePrice(
                new CalculateDistanceHaversine(rideCoordResolver),
                new HoursResolverFake(),
                requestRide
        );

        final RidePriceResponse price = ridePrice.calculate(
                "87023060",
                "87035350"
        );

        Assertions.assertThat(price.price()).isCloseTo(new BigDecimal("8.40"), Offset.offset(new BigDecimal("0.1")));
    }

    private static class HoursResolverFake implements HoursResolver {

        @Override
        public boolean isMidnight() {
            return false;
        }
    }

}
