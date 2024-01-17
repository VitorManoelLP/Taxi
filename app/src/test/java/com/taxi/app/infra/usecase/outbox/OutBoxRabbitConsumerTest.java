package com.taxi.app.infra.usecase.outbox;

import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.UUID;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.shaded.com.github.dockerjava.core.MediaType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.taxi.app.application.usecase.Payload;
import com.taxi.app.extension.ContainerBaseExtension;
import com.taxi.app.infra.entity.OutBoxEntity;

import jakarta.persistence.EntityManager;

@Sql(statements = {
        "INSERT INTO taxi.outbox "
                + "(id, payload, sent, exchange, date_produce, date_consume, error_message, has_error, requested_by)\n"
                + "VALUES('f6157ef6-5b8e-437b-87c0-ffd8e2bb5d1b'::uuid, '{\"attribute\":\"teste\",\"topic\":\"topico\"}', true, 'RIDE_ACCEPTED_TOPIC', '2024-01-16 00:01:15.863456', NULL, NULL, false, 'foo@gmail.com');"
})
public class OutBoxRabbitConsumerTest extends ContainerBaseExtension {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void consume() throws JsonProcessingException {

        final OutBoxEntity outBoxEntity = entityManager.find(OutBoxEntity.class,
                UUID.fromString("f6157ef6-5b8e-437b-87c0-ffd8e2bb5d1b"));

        final OutBoxConsumerFake outBoxConsumerFake = new OutBoxConsumerFake(
                entityManager,
                objectMapper
        );

        final MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType(MediaType.APPLICATION_JSON.getMediaType());

        outBoxConsumerFake.consumes(new Message(objectMapper.writeValueAsString(outBoxEntity).getBytes(StandardCharsets.UTF_8),
                messageProperties));

        Assertions.assertThat(outBoxConsumerFake.getPayloadReceived())
                .usingRecursiveComparison()
                .isEqualTo(new OutBoxPayloadFake("teste"));

    }

    @Test
    public void shouldSaveErrorWhenConsumesFailed() throws JsonProcessingException {

        final OutBoxEntity outBoxEntity = entityManager.find(OutBoxEntity.class,
                UUID.fromString("f6157ef6-5b8e-437b-87c0-ffd8e2bb5d1b"));

        final OutBoxConsumerFakeError outBoxConsumerFake = new OutBoxConsumerFakeError(
                entityManager,
                objectMapper
        );

        final MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType(MediaType.APPLICATION_JSON.getMediaType());

        outBoxConsumerFake.consumes(new Message(objectMapper.writeValueAsString(outBoxEntity).getBytes(StandardCharsets.UTF_8),
                messageProperties));

        entityManager.flush();

        Assertions.assertThat(outBoxEntity.isHasError()).isTrue();
        Assertions.assertThat(outBoxEntity.getErrorMessage()).isEqualTo("Error");
    }

    public static class OutBoxConsumerFake extends OutBoxConsumer<OutBoxPayloadFake> {

        private OutBoxPayloadFake payloadReceived;

        public OutBoxConsumerFake(EntityManager entityManager,
                ObjectMapper objectMapper) {
            super(entityManager, objectMapper);
        }

        @Override
        protected void consume(OutBoxPayloadFake payload, String requestedBy) {
            payloadReceived = payload;
        }

        public OutBoxPayloadFake getPayloadReceived() {
            return payloadReceived;
        }

        @Override
        public boolean equals(Object object) {
            if (this == object)
                return true;
            if (object == null || getClass() != object.getClass())
                return false;
            OutBoxConsumerFake that = (OutBoxConsumerFake) object;
            return Objects.equals(payloadReceived, that.payloadReceived);
        }

        @Override
        public int hashCode() {
            return Objects.hash(payloadReceived);
        }

    }

    public static class OutBoxConsumerFakeError extends OutBoxConsumer<OutBoxPayloadFake> {

        public OutBoxConsumerFakeError(EntityManager entityManager,
                ObjectMapper objectMapper) {
            super(entityManager, objectMapper);
        }

        @Override
        protected void consume(OutBoxPayloadFake payload, String requestedBy) {
            throw new IllegalArgumentException("Error");
        }

    }

    public static class OutBoxPayloadFake implements Payload {

        private String attribute;

        public OutBoxPayloadFake(String attribute) {
            this.attribute = attribute;
        }

        public OutBoxPayloadFake() {
        }

        @Override
        public String getTopic() {
            return "topico";
        }

        public String getAttribute() {
            return attribute;
        }
    }

}
