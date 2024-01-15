package com.taxi.app.infra.entity;

import java.math.BigDecimal;
import java.util.UUID;

import com.taxi.app.infra.validation.CustomValidator;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@CustomValidator(message = "Requested Account linked named passenger must be a passenger", conditionalProperty = "passenger.accountType.isPassenger()", activateGroups = RequestedRidesEntity.isPassengerValidator.class)
@Table(name = "REQUESTED_RIDES")
public class RequestedRidesEntity {

    @Id
    private UUID id;

    @NotEmpty
    private String fromName;

    @NotEmpty
    private String toName;

    @NotEmpty
    private String cepFrom;

    @NotEmpty
    private String cepTo;

    @NotNull
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "PASSENGER_ID", nullable = false)
    private AccountEntity passenger;

    public interface isPassengerValidator {
    }
}
