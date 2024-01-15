package com.taxi.app.infra.usecase;

import java.math.BigDecimal;
import java.util.UUID;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.taxi.app.Fixture.Fixtures;
import com.taxi.app.application.usecase.persistence.SaveAccountUsecase;
import com.taxi.app.application.usecase.persistence.SaveCoord;
import com.taxi.app.application.usecase.persistence.SaveRide;
import com.taxi.app.application.usecase.ride.state.RideState;
import com.taxi.app.domain.Account;
import com.taxi.app.domain.Coord;
import com.taxi.app.domain.Ride;
import com.taxi.app.domain.enums.RideStatus;
import com.taxi.app.extension.ContainerBaseExtension;
import com.taxi.app.infra.entity.RideEntity;
import com.taxi.app.infra.repository.CoordRepository;

public class SaveRideTest extends ContainerBaseExtension {

    @Autowired
    private SaveRide saveRide;

    @Autowired
    private SaveAccountUsecase saveAccountUsecase;

    @Autowired
    private SaveCoord saveCoord;

    @Autowired
    private CoordRepository coordRepository;

    @AfterEach
    public void afterEach() {
        coordRepository.deleteAll();
    }

    @Test
    public void shouldSave() {

        Account driver = Fixtures.createDriver();
        Account passenger = Fixtures.createPassanger();
        Coord from = new Coord("1", "Rua Foo", -100.00, -120.00, "");
        Coord to = new Coord("2","Rua Foo2", -20.00, -23.00, "");

        saveCoord.save(from);
        saveCoord.save(to);

        saveAccountUsecase.save(driver, "1234");
        saveAccountUsecase.save(passenger, "1234");

        getEm().flush();

        UUID idDriver = getEm().createQuery("SELECT id FROM AccountEntity account WHERE account.email = :driver",
                        UUID.class)
                .setParameter("driver", driver.email().getValue())
                .getSingleResult();

        UUID idPassenger = getEm().createQuery("SELECT id FROM AccountEntity account WHERE account.email = :passenger",
                        UUID.class)
                .setParameter("passenger", passenger.email().getValue())
                .getSingleResult();

        Ride ride = new Ride(new BigDecimal("100"), idDriver, idPassenger,
                RideState.of(RideStatus.STARTED), from, to);

        saveRide.save(ride);

        final RideEntity rideSaved = getEm().createQuery(
                        "SELECT entity FROM RideEntity entity WHERE entity.passenger.id = :idPassenger AND entity.driver.id = :idDriver ", RideEntity.class)
                .setParameter("idPassenger", idPassenger)
                .setParameter("idDriver", idDriver)
                .getSingleResult();

        Assertions.assertThat(rideSaved).isNotNull();
    }

    @Test
    public void shouldThrowWhenDriverNotHasAccountTypeDriver() {

        Account passenger = Fixtures.createPassanger();
        Coord from = new Coord("1","Rua Foo", -100.00, -120.00, "");
        Coord to = new Coord("2","Rua Foo 2", -20.00, -23.00, "");

        saveCoord.save(from);
        saveCoord.save(to);

        saveAccountUsecase.save(passenger, "1234");

        getEm().flush();

        UUID idPassenger = getEm().createQuery("SELECT id FROM AccountEntity account WHERE account.email = :passenger",
                        UUID.class)
                .setParameter("passenger", passenger.email().getValue())
                .getSingleResult();

        Ride ride = new Ride(new BigDecimal("100"), idPassenger, idPassenger,
                RideState.of(RideStatus.STARTED), from, to);

        Assertions.assertThatThrownBy(() -> {
            saveRide.save(ride);
            getEm().flush();
        }).hasMessageContaining("Ride Account linked named driver must be a driver");

    }

    @Test
    public void shouldThrowWhenPassengerNotHasAccountTypePassenger() {

        Account driver = Fixtures.createDriver();
        Coord from = new Coord("1","Rua Foo", -100.00, -120.00, "");
        Coord to = new Coord("2","Rua Foo2", -20.00, -23.00, "");

        saveCoord.save(from);
        saveCoord.save(to);

        saveAccountUsecase.save(driver, "1234");

        getEm().flush();

        UUID idDriver = getEm().createQuery("SELECT id FROM AccountEntity account WHERE account.email = :driver",
                        UUID.class)
                .setParameter("driver", driver.email().getValue())
                .getSingleResult();

        Ride ride = new Ride(new BigDecimal("100"), idDriver, idDriver,
                RideState.of(RideStatus.STARTED), from, to);

        Assertions.assertThatThrownBy(() -> {
            saveRide.save(ride);
            getEm().flush();
        }).hasMessageContaining("Ride Account linked named passenger must be a passenger");

    }

}
