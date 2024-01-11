package com.taxi.app.domain.object;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class CepVO {

    private final String value;

    public CepVO(String value) {
        if (value.length() != 8) {
            throw new IllegalArgumentException("Incorrect CEP value");
        }
        this.value = value;
    }

}
