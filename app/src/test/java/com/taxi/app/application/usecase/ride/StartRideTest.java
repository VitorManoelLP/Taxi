package com.taxi.app.application.usecase.ride;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.taxi.app.application.usecase.SaveRide;
import com.taxi.app.domain.Coord;
import com.taxi.app.domain.Ride;
import com.taxi.app.infra.repository.CoordRepository;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StartRideTest {

    private StartRide startRide;

    private SaveRideFake saveRideFake;

    @Mock
    private CoordRepository coordRepository;

    @BeforeEach
    public void setup() {
        saveRideFake = new SaveRideFake();
        startRide = new StartRide(saveRideFake, coordRepository);
    }

    @Test
    public void shouldStartRide() {

        UUID idDriver = UUID.randomUUID();
        UUID idPassenger = UUID.randomUUID();
        BigDecimal price = new BigDecimal("100");
        Coord from = new Coord("123","Rua Foo", -12.324, -32.423, "");
        Coord to = new Coord("234","Rua Foo2", -31.324, -32.487, "");

        when(coordRepository.findByCep("123")).thenReturn(Optional.of(from));
        when(coordRepository.findByCep("234")).thenReturn(Optional.of(to));

        startRide.start(idDriver, idPassenger, price, "123", "234");

        Assertions.assertThat(saveRideFake).extracting(SaveRideFake::getRide)
                .extracting(Ride::idDriver, Ride::idPassenger, Ride::price, Ride::to, Ride::from)
                .containsOnly(idDriver, idPassenger, price, to, from);
    }

    private static class SaveRideFake implements SaveRide {

        private Ride ride;

        @Override
        public void save(Ride ride) {
            this.ride = ride;
        }

        public Ride getRide() {
            return ride;
        }
    }

}
