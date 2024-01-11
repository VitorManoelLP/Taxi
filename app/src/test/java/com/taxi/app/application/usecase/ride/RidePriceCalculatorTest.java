package com.taxi.app.application.usecase.ride;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.taxi.app.application.usecase.ride.resolvers.RidePriceResolver;
import com.taxi.app.domain.Coord;
import com.taxi.app.dto.RidePriceResponse;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RidePriceCalculatorTest {

    private RidePriceCalculator ridePriceCalculator;
    @Mock
    private RidePrice ridePrice;

    @Mock
    private RidePriceResolver ridePriceResolver;

    @BeforeEach
    public void setup() {
        ridePriceCalculator = new RidePriceCalculator(ridePrice, ridePriceResolver);
    }

    @Test
    public void shouldCalculate() {
        when(ridePriceResolver.resolve(any())).thenReturn(new Coord("1","Rua Foo", -100.00, -120.00));
        when(ridePrice.calculate(any(), any())).thenReturn(new BigDecimal("10"));
        RidePriceResponse calculated = ridePriceCalculator.calculate("87035350", "87023060");
        Assertions.assertEquals(calculated.price(), new BigDecimal("10"));
        Assertions.assertEquals(calculated.fromName(),"Rua Foo");
        Assertions.assertEquals(calculated.toName(), "Rua Foo");
    }

}
