package com.taxi.app.application.usecase.distance;

import java.math.BigDecimal;

import org.assertj.core.api.Assertions;
import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Test;

import com.taxi.app.application.usecase.fare.HoursResolver;
import com.taxi.app.application.usecase.ride.RidePrice;
import com.taxi.app.domain.Coord;

public class RidePriceTest {

    @Test
    public void calculate() {

        final RidePrice ridePrice = new RidePrice(
                new CalculateDistanceHaversine(),
                new HoursResolverFake()
        );

        final BigDecimal price = ridePrice.calculate(new Coord("1", "Rua Foo", -25.4284, -49.2733), new Coord("1", "Rua Foo2", -23.4273, -51.9375));

        Assertions.assertThat(price).isCloseTo(new BigDecimal("735.00"), Offset.offset(new BigDecimal("0.1")));
    }

    @Test
    public void calculate2() {

        final RidePrice ridePrice = new RidePrice(
                new CalculateDistanceHaversine(),
                new HoursResolverFake()
        );

        final BigDecimal price = ridePrice.calculate(
                new Coord("1", "Rua Foo", -23.3965, -51.9346),
                new Coord("1", "Rua Foo2", -23.3885, -51.8967)
        );

        Assertions.assertThat(price).isCloseTo(new BigDecimal("8.40"), Offset.offset(new BigDecimal("0.1")));
    }

    private static class HoursResolverFake implements HoursResolver {

        @Override
        public boolean isMidnight() {
            return false;
        }
    }

}
