package com.taxi.app.infra.usecase.outbox;

import java.lang.reflect.ParameterizedType;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taxi.app.application.usecase.Payload;
import com.taxi.app.infra.entity.OutBoxEntity;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@RequiredArgsConstructor
public abstract class OutBoxRabbitConsumer<T extends Payload> {

    @SuppressWarnings("unchecked")
    private final Class<T> payloadType = (Class<T>) ((ParameterizedType) getClass()
            .getGenericSuperclass()).getActualTypeArguments()[0];

    protected final EntityManager entityManager;
    protected final ObjectMapper objectMapper;

    @RabbitHandler(isDefault = true)
    public void consumes(Message message) {
        log.debug("Consume queue by outbox");
        final OutBoxEntity outbox = getOutbox(message);
        try {
            final T payload = objectMapper.readValue(outbox.getPayload(), payloadType);
            log.debug("Processing topic {} with payload: {}", payload.getTopic(), payload);
            consume(payload, outbox.getRequestedBy());
        } catch (Exception exception) {
            outbox.withError(exception.getMessage());
            entityManager.merge(outbox);
            log.error("An error occurred when trying to consume the queue", exception);
        }
    }

    @SneakyThrows
    private OutBoxEntity getOutbox(Message message) {
        return objectMapper.readValue(message.getBody(), OutBoxEntity.class);
    }

    protected abstract void consume(T payload, String requestedBy);

}
