package com.taxi.app.infra.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.taxi.app.infra.entity.OutBoxEntity;

@Repository
public interface OutBoxRepository extends JpaRepository<OutBoxEntity, UUID> {
    List<OutBoxEntity> findAllBySentFalse();
    Page<OutBoxEntity> findAllByHasErrorTrue(Pageable pageable);
}
