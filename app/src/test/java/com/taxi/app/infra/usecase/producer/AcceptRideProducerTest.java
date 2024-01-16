package com.taxi.app.infra.usecase.producer;

import java.util.List;
import java.util.UUID;

import org.assertj.core.api.Assertions;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.taxi.app.dto.AcceptRidePayload;
import com.taxi.app.extension.ContainerBaseExtension;
import com.taxi.app.infra.config.JacksonConfiguration;
import com.taxi.app.infra.entity.OutBoxEntity;
import com.taxi.app.infra.repository.OutBoxRepository;

public class AcceptRideProducerTest extends ContainerBaseExtension {

    @Autowired
    private AcceptRideProducer acceptRideProducer;

    @Autowired
    private OutBoxRepository outBoxRepository;

    @Test
    public void producerWithAmqp() throws JsonProcessingException {
        ObjectMapper objectMapper = new JacksonConfiguration().objectMapper();
        final UUID requestID = UUID.randomUUID();
        acceptRideProducer.accept(requestID);
        final AcceptRidePayload acceptRidePayload = new AcceptRidePayload(requestID);
        final List<OutBoxEntity> allBySendedFalse = outBoxRepository.findAllBySentFalse();
        Assertions.assertThat(allBySendedFalse)
                .hasSize(1)
                .extracting(OutBoxEntity::getExchange, OutBoxEntity::getPayload)
                .containsOnly(Tuple.tuple(
                        AcceptRidePayload.TOPIC,
                        objectMapper.writeValueAsString(acceptRidePayload)
                ));
    }

}
