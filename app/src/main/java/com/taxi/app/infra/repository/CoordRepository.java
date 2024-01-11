package com.taxi.app.infra.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.taxi.app.domain.Coord;
import com.taxi.app.infra.entity.CoordEntity;

@Repository
public interface CoordRepository extends JpaRepository<CoordEntity, UUID> {

    boolean existsByLatitudeAndLongitude(double latitude, double longitude);

    Optional<Coord> findByCep(String cep);

}
