package com.taxi.app.dto.amqp;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.taxi.app.application.usecase.Payload;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AcceptRidePayload(UUID requestId) implements Payload {
    public static final String TOPIC = Exchanges.ACCEPT_RIDE_TOPIC;

    @Override
    public String getTopic() {
        return TOPIC;
    }

}
