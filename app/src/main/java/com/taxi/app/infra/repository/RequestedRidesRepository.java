package com.taxi.app.infra.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.taxi.app.infra.entity.RequestedRidesEntity;

@Repository
public interface RequestedRidesRepository extends JpaRepository<RequestedRidesEntity, UUID> {
}
