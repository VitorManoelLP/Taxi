package com.taxi.app.domain.object;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EmailVOTest {

    @Test
    public void validateEmail() {
        Assertions.assertDoesNotThrow(() -> new EmailVO("foo@gmail.com"));

        final List<String> invalidEmailInputs = List.of(
                "foo@gmail",
                "foo@gmail.com.",
                "foo@@gmail.com",
                "",
                "foo"
        );

        for (final String invalidInput : invalidEmailInputs) {
            org.assertj.core.api.Assertions.assertThatThrownBy(() -> new EmailVO(invalidInput))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("Invalid e-mail");
        }

    }

}