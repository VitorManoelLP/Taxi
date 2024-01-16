package com.taxi.app.infra.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.taxi.app.infra.entity.OutBoxLockEntity;

@Repository
public interface OutBoxLockRepository extends JpaRepository<OutBoxLockEntity, UUID> {

    default boolean isLocked() {
        final OutBoxLockEntity outBoxLockEntity = findLock().orElse(new OutBoxLockEntity());
        save(outBoxLockEntity);
        return outBoxLockEntity.isLocked();
    }

    default void lock() {
        final OutBoxLockEntity outBoxLockEntity = findLock().orElseThrow(
                () -> new IllegalStateException("OutBox lock not has created"));
        outBoxLockEntity.lock();
        save(outBoxLockEntity);
    }

    default void unlock() {
        final OutBoxLockEntity outBoxLockEntity = findLock().orElseThrow(
                () -> new IllegalStateException("OutBox lock not has created"));
        outBoxLockEntity.unlock();
        save(outBoxLockEntity);
    }

    @Query(value = "SELECT OUTBOX.* FROM TAXI.OUTBOX_LOCK OUTBOX LIMIT 1", nativeQuery = true)
    Optional<OutBoxLockEntity> findLock();

}
