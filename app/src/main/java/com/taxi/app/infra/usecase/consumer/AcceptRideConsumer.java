package com.taxi.app.infra.usecase.consumer;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taxi.app.application.usecase.ride.AcceptRide;
import com.taxi.app.dto.amqp.AcceptRidePayload;
import com.taxi.app.dto.amqp.Exchanges;
import com.taxi.app.infra.usecase.outbox.OutBoxRabbitConsumer;

import jakarta.persistence.EntityManager;

@Component
@RabbitListener(
        bindings = @QueueBinding(value = @Queue(value = AcceptRidePayload.TOPIC + "_QUEUE"),
        exchange = @Exchange(value = Exchanges.ACCEPT_RIDE_TOPIC, type = ExchangeTypes.TOPIC, declare = Exchange.FALSE), key = "")
)
public class AcceptRideConsumer extends OutBoxRabbitConsumer<AcceptRidePayload> {

    private final AcceptRide acceptRide;

    public AcceptRideConsumer(EntityManager entityManager, ObjectMapper objectMapper, AcceptRide acceptRide) {
        super(entityManager, objectMapper);
        this.acceptRide = acceptRide;
    }

    @Override
    public void consume(AcceptRidePayload payload, String requestedBy) {
        acceptRide.accept(payload.requestId(), requestedBy);
    }

}
