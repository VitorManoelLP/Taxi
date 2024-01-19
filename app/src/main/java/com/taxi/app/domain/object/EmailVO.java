package com.taxi.app.domain.object;

import java.util.regex.Pattern;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Getter
@ToString(of = "value")
@EqualsAndHashCode
public class EmailVO {

    private static final Pattern REGEX_VALIDATOR_EMAIL = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    private final String value;

    public EmailVO(@NonNull String value) {
        this.value = validateEmaiL(value);
    }

    private String validateEmaiL(final String email) {
        if (!REGEX_VALIDATOR_EMAIL.matcher(email).matches()) {
            throw new IllegalArgumentException("Invalid e-mail");
        }
        return email;
    }

}
