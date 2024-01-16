package com.taxi.app.infra.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.security.core.context.SecurityContextHolder;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Entity
@Getter
@ToString
@EqualsAndHashCode(of = "id")
@Table(name = "OUTBOX")
@AllArgsConstructor
public class OutBoxEntity {

    @Id
    private final UUID id = UUID.randomUUID();

    @NotEmpty
    private String payload;
    private boolean sent = false;

    @NotEmpty
    private String exchange;

    @NotNull
    private final LocalDateTime dateProduce = LocalDateTime.now();

    private LocalDateTime dateConsume;

    private boolean hasError = false;
    private String errorMessage;

    @NotEmpty
    private String requestedBy;

    public void send() {
        dateConsume = LocalDateTime.now();
        sent = true;
    }

    public void withError(final String errorMessage) {
        this.errorMessage = errorMessage;
        hasError = true;
    }

    public OutBoxEntity(String payload, String exchange) {
        this.payload = payload;
        this.exchange = exchange;
        this.requestedBy = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
    }

    public OutBoxEntity() {

    }

}
