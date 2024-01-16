package com.taxi.app.application.usecase.fare;

import java.time.LocalDateTime;

import com.taxi.app.application.usecase.HoursResolver;

public class LocalDateTimeHoursResolver implements HoursResolver {
    @Override
    public boolean isMidnight() {
        int hours = LocalDateTime.now().getHour();
        return hours >= 21;
    }
}
