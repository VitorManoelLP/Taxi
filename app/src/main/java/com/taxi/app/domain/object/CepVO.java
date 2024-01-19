package com.taxi.app.domain.object;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Getter
@ToString(of = "value")
@EqualsAndHashCode
public class CepVO {

    private final String value;

    public CepVO(@NonNull String value) {
        if (value.length() != 8) {
            throw new IllegalArgumentException("Incorrect CEP value");
        }
        this.value = value;
    }

}
