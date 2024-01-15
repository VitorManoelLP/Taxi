package com.taxi.app.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.taxi.app.application.usecase.AccountManager;
import com.taxi.app.application.usecase.distance.CalculateDistance;
import com.taxi.app.application.usecase.fare.HoursResolver;
import com.taxi.app.application.usecase.fare.LocalDateTimeHoursResolver;
import com.taxi.app.application.usecase.ride.RequestRide;
import com.taxi.app.application.usecase.ride.RidePrice;
import com.taxi.app.application.usecase.ride.StartRide;
import com.taxi.app.infra.repository.CoordRepository;
import com.taxi.app.infra.usecase.SaveRide;

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

    @Bean
    public StartRide startRide(SaveRide saveRide, CoordRepository coordRepository) {
        return new StartRide(saveRide, coordRepository);
    }
}
