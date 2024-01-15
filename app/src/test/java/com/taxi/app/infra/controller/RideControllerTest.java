package com.taxi.app.infra.controller;

import java.util.List;
import java.util.UUID;

import org.assertj.core.api.Assertions;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import com.taxi.app.Fixture.Fixtures;
import com.taxi.app.extension.ContainerBaseExtension;
import com.taxi.app.infra.entity.RequestedRidesEntity;
import com.taxi.app.infra.repository.RequestedRidesRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@AutoConfigureMockMvc
@Sql(statements = {
        "INSERT INTO COORD(LATITUDE, LONGITUDE, COORD_NAME, CEP) VALUES(-23.3965567, -51.9346865, 'Foo', '87023060');",
        "INSERT INTO COORD(LATITUDE, LONGITUDE, COORD_NAME, CEP) VALUES(-23.3885031, -51.8967807, 'Foo 2', '87035350');",
        "INSERT INTO ACCOUNT(ID, IMAGE_PATH, EMAIL, PHONE, NAME, ACCOUNT_TYPE, PASSWORD) VALUES('93e98369-ebd5-41ce-9335-4174395cd7dd', NULL, 'foo@gmail.com', '44555555555', 'FOO', 2, '234');"
}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(statements = {
        "DELETE FROM COORD",
        "DELETE FROM ACCOUNT"
}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_CLASS)
public class RideControllerTest extends ContainerBaseExtension {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RequestedRidesRepository requestedRidesRepository;

    @Test
    public void calculate() throws Exception {

        Fixtures.setSecurityContext("foo@gmail.com");

        final String response = mockMvc.perform(get("/api/ride/calculator")
                        .param("from", "87023060")
                        .param("to", "87035350"))
                .andReturn().getResponse().getContentAsString();

        final List<RequestedRidesEntity> requestedRide = requestedRidesRepository.findAll();

        Assertions.assertThat(response).contains("\"fromName\":\"Foo\",\"toName\":\"Foo 2\",\"price\":8.34");
        Assertions.assertThat(requestedRide)
                .hasSize(1)
                .extracting(RequestedRidesEntity::getCepFrom, RequestedRidesEntity::getCepTo, s -> s.getPassenger().getId())
                .containsOnly(Tuple.tuple(
                        "87023060",
                        "87035350",
                        UUID.fromString("93e98369-ebd5-41ce-9335-4174395cd7dd")
                ));

        Fixtures.clearAll();
    }

}
