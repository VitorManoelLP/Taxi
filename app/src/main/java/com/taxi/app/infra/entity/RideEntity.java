package com.taxi.app.infra.entity;

import java.math.BigDecimal;
import java.util.UUID;

import com.taxi.app.domain.enums.RideStatus;
import com.taxi.app.infra.validation.CustomValidator;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "rides")
@CustomValidator(message = "Ride Account linked named driver must be a driver", conditionalProperty = "driver.accountType.isDriver()", activateGroups = RideEntity.isDriverValidator.class)
@CustomValidator(message = "Ride Account linked named passenger must be a passenger", conditionalProperty = "passenger.accountType.isPassenger()", activateGroups = RideEntity.isPassengerValidator.class)
@AllArgsConstructor
@NoArgsConstructor
public class RideEntity {

    @Id
    private UUID id;

    @NotNull
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "id_driver", nullable = false)
    private AccountEntity driver;

    @ManyToOne
    @JoinColumn(name = "id_passenger", nullable = false)
    private AccountEntity passenger;

    @NotNull
    private RideStatus rideStatus;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(
                    name = "latitude_from",
                    referencedColumnName = "latitude"),
            @JoinColumn(
                    name = "longitude_from",
                    referencedColumnName = "longitude")
    })
    private CoordEntity from;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(
                    name = "latitude_to",
                    referencedColumnName = "latitude"),
            @JoinColumn(
                    name = "longitude_to",
                    referencedColumnName = "longitude")
    })
    private CoordEntity to;

    public interface isDriverValidator {
    }

    public interface isPassengerValidator {
    }

}
