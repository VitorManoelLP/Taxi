package com.taxi.app.infra.usecase.outbox;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import com.taxi.app.extension.ContainerBaseExtension;
import com.taxi.app.infra.entity.OutBoxLockEntity;
import com.taxi.app.infra.repository.OutBoxLockRepository;

@Sql(statements = {
        "INSERT INTO taxi.outbox "
                + "(id, payload, sent, exchange, date_produce, date_consume, error_message, has_error, requested_by)\n"
                + "VALUES('f6157ef6-5b8e-437b-87c0-ffd8e2bb5d1b'::uuid, '{\"requestId\":\"fb5b6fe1-81f4-4418-a7ef-f28c97eb0c43\",\"topic\":\"RIDE_ACCEPTED_TOPIC\"}', false, 'RIDE_ACCEPTED_TOPIC', '2024-01-16 00:01:15.863', NULL, NULL, false, 'foo@gmail.com');"
})
public class OutBoxPollingTest extends ContainerBaseExtension {

    @Autowired
    private OutBoxPolling outBoxPolling;

    @Autowired
    private OutBoxLockRepository outBoxLockRepository;

    @Test
    public void poll() {
        outBoxPolling.poll();
        Assertions.assertThat(outBoxLockRepository.isLocked()).isFalse();
    }

    @Test
    public void shouldNotSendMessageIfOutBoxIsLocked() {
        outBoxLockRepository.save(new OutBoxLockEntity());
        outBoxLockRepository.lock();
        outBoxPolling.poll();
        Assertions.assertThat(outBoxLockRepository.isLocked()).isTrue();
    }

}
