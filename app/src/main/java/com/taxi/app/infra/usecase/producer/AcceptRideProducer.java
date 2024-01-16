package com.taxi.app.infra.usecase.producer;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.taxi.app.dto.AcceptRidePayload;
import com.taxi.app.infra.usecase.outbox.OutBoxProducer;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AcceptRideProducer {

    private final OutBoxProducer outBoxProducer;

    public void accept(UUID requestID) {
        final AcceptRidePayload acceptRidePayload = new AcceptRidePayload(requestID);
        outBoxProducer.produce(acceptRidePayload);
    }

}
