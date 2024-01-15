package com.taxi.app.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.taxi.app.application.usecase.AccountManager;
import com.taxi.app.application.usecase.distance.CalculateDistance;
import com.taxi.app.application.usecase.fare.HoursResolver;
import com.taxi.app.application.usecase.fare.LocalDateTimeHoursResolver;
import com.taxi.app.application.usecase.ride.RequestRide;
import com.taxi.app.application.usecase.ride.RidePrice;

@Configuration
public class SpringComponentConfig {

    @Bean
    public RidePrice ridePrice(CalculateDistance calculateDistance, HoursResolver hoursResolver, RequestRide requestRide, AccountManager accountManager) {
        return new RidePrice(calculateDistance, hoursResolver, requestRide, accountManager);
    }

    @Bean
    public HoursResolver hoursResolver() {
        return new LocalDateTimeHoursResolver();
    }

}
