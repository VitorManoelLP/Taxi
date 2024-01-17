package com.taxi.app.infra.usecase.outbox;

import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.taxi.app.infra.entity.OutBoxEntity;
import com.taxi.app.infra.repository.OutBoxLockRepository;
import com.taxi.app.infra.repository.OutBoxRepository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Transactional
@RequiredArgsConstructor
public class OutBoxPolling {

    private final OutBoxRepository outBoxRepository;
    private final RabbitTemplate rabbitTemplate;
    private final OutBoxLockRepository outBoxLockRepository;
    private final EntityManager entityManager;

    @Scheduled(fixedDelay = 30000L)
    public void poll() {
        log.info("Polling outbox looking for not sended messages");
        if (outBoxLockRepository.isLocked()) {
            log.info("Already has outbox processing, waiting for unlock process");
            return;
        }
        final List<OutBoxEntity> allBySendedFalse = outBoxRepository.findAllBySentFalse();
        if (allBySendedFalse.isEmpty()) {
            return;
        }
        outBoxLockRepository.lock();
        allBySendedFalse.forEach(outbox -> {
            rabbitTemplate.convertAndSend(outbox.getExchange(), "", outbox);
            outbox.send();
            entityManager.merge(outbox);
        });
        outBoxLockRepository.unlock();
    }

}
