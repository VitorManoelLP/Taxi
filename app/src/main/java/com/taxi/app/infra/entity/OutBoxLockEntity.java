package com.taxi.app.infra.entity;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "OUTBOX_LOCK")
@AllArgsConstructor
@NoArgsConstructor
public class OutBoxLockEntity {

    @Id
    private UUID id = UUID.randomUUID();

    private boolean locked = false;

    public void lock() {
        locked = true;
    }

    public void unlock() {
        locked = false;
    }
}
