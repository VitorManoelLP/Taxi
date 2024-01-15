package com.taxi.app.infra.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import com.taxi.app.extension.ContainerBaseExtension;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@AutoConfigureMockMvc
@Sql(statements = {
        "INSERT INTO COORD(LATITUDE, LONGITUDE, COORD_NAME, CEP) VALUES(-23.3965567, -51.9346865, 'Foo', '87023060');",
        "INSERT INTO COORD(LATITUDE, LONGITUDE, COORD_NAME, CEP) VALUES(-23.3885031, -51.8967807, 'Foo 2', '87035350');"
}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(statements = {
        "DELETE FROM COORD"
}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_CLASS)
public class RideControllerTest extends ContainerBaseExtension {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void calculate() throws Exception {

        final String response = mockMvc.perform(get("/ride/calculator")
                        .param("from", "87023060")
                        .param("to", "87035350"))
                .andReturn().getResponse().getContentAsString();

        Assertions.assertThat(response).isEqualTo("{\"fromName\":\"Foo\",\"toName\":\"Foo 2\",\"price\":8.34}");
    }

}
