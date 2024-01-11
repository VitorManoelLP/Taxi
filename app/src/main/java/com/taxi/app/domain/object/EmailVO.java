package com.taxi.app.domain.object;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class EmailVO {

    private final String value;

    public EmailVO(String value) {
        this.value = value;
    }

}
