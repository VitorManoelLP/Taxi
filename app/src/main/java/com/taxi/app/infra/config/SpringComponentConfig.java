package com.taxi.app.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.taxi.app.application.usecase.distance.CalculateDistance;
import com.taxi.app.application.usecase.distance.GeolocationCalculator;
import com.taxi.app.application.usecase.fare.HoursResolver;
import com.taxi.app.application.usecase.fare.LocalDateTimeHoursResolver;
import com.taxi.app.application.usecase.persistence.SaveCoord;
import com.taxi.app.application.usecase.ride.RidePrice;
import com.taxi.app.infra.repository.CoordRepository;
import com.taxi.app.infra.usecase.RideCoordResolver;

@Configuration
public class SpringComponentConfig {

    @Bean
    public RideCoordResolver ridePriceResolver(GeolocationCalculator geolocationCalculator, CoordRepository coordRepository, SaveCoord saveCoord) {
        return new RideCoordResolver(geolocationCalculator, coordRepository, saveCoord);
    }

    @Bean
    public RidePrice ridePrice(CalculateDistance calculateDistance, HoursResolver hoursResolver) {
        return new RidePrice(calculateDistance, hoursResolver);
    }

    @Bean
    public HoursResolver hoursResolver() {
        return new LocalDateTimeHoursResolver();
    }

}
