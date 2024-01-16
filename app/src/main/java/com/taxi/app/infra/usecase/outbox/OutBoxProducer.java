package com.taxi.app.infra.usecase.outbox;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.taxi.app.application.usecase.Payload;
import com.taxi.app.infra.entity.OutBoxEntity;
import com.taxi.app.infra.repository.OutBoxRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class OutBoxProducer {

    private final ObjectMapper objectMapper;
    private final OutBoxRepository outBoxRepository;

    public <T extends Payload> void produce(T payload) {
        try {
            final OutBoxEntity outBoxEntity = new OutBoxEntity(objectMapper.writeValueAsString(payload), payload.getTopic());
            outBoxRepository.save(outBoxEntity);
            log.debug("Outbox saved with id {}", outBoxEntity.getId());
        } catch (JsonProcessingException e) {
            log.error("Failed produce message to topic {}", payload.getTopic(), e);
        }
    }

}
