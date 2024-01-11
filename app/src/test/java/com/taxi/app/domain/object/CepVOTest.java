package com.taxi.app.domain.object;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CepVOTest {

    @Test
    public void shouldThrowWhenCepIsIncorrect() {
        Assertions.assertDoesNotThrow(() -> new CepVO("87023060"));
        org.assertj.core.api.Assertions.assertThatThrownBy(() -> new CepVO("8702306"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Incorrect CEP value");
    }

}
