package com.taxi.app.domain.object;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@Getter
@EqualsAndHashCode
public class PhoneVO {

    int ddd;
    long number;
    int countryCode;

    public static PhoneVO of(@NonNull String phone) {
        String countryNumber = phone.substring(0, 2);
        String ddd = phone.substring(2, 4);
        String number = phone.substring(4);
        return new PhoneVO(
                Integer.parseInt(ddd),
                Long.parseLong(number),
                Integer.parseInt(countryNumber)
        );
    }

    public PhoneVO(int ddd, long number, int countryCode) {
        this.ddd = validateDDDAndCountryCode(ddd, "Invalid DDD");
        this.countryCode = validateDDDAndCountryCode(countryCode, "Invalid Country Code");
        this.number = validateNumber(number);
    }

    private static int validateDDDAndCountryCode(final int dddCountryNumber, final String message) {
        if (String.valueOf(dddCountryNumber).length() > 2) {
            throw new IllegalArgumentException(message);
        }

        return dddCountryNumber;
    }

    private static long validateNumber(final long phoneNumber) {
        if (String.valueOf(phoneNumber).length() > 9) {
            throw new IllegalArgumentException("Invalid Phone Number");
        }

        return phoneNumber;
    }

    @Override
    public String toString() {
        return String.format("%s%s%s", countryCode, ddd, number);
    }

}
