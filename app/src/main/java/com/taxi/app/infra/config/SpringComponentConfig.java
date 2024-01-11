package com.taxi.app.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.taxi.app.application.usecase.distance.CalculateDistance;
import com.taxi.app.application.usecase.distance.CalculateDistanceHaversine;
import com.taxi.app.application.usecase.fare.HoursResolver;
import com.taxi.app.application.usecase.fare.LocalDateTimeHoursResolver;
import com.taxi.app.application.usecase.persistence.SaveCoord;
import com.taxi.app.application.usecase.ride.RidePrice;
import com.taxi.app.application.usecase.ride.RidePriceCalculator;
import com.taxi.app.application.usecase.ride.resolvers.RidePriceResolver;
import com.taxi.app.infra.clients.GeolocationPythonClient;
import com.taxi.app.infra.repository.CoordRepository;
import com.taxi.app.infra.usecase.resolvers.PythonRidePriceResolver;

@Configuration
public class SpringComponentConfig {

    @Bean
    public RidePriceCalculator ridePriceCalculator(RidePrice ridePrice, RidePriceResolver ridePriceResolver) {
        return new RidePriceCalculator(ridePrice, ridePriceResolver);
    }

    @Bean
    public RidePriceResolver ridePriceResolver(GeolocationPythonClient geolocationPythonClient, CoordRepository coordRepository, SaveCoord saveCoord) {
        return new PythonRidePriceResolver(geolocationPythonClient, coordRepository, saveCoord);
    }

    @Bean
    public RidePrice ridePrice(CalculateDistance calculateDistance, HoursResolver hoursResolver) {
        return new RidePrice(calculateDistance, hoursResolver);
    }

    @Bean
    public HoursResolver hoursResolver() {
        return new LocalDateTimeHoursResolver();
    }

    @Bean
    public CalculateDistance calculateDistance() {
        return new CalculateDistanceHaversine();
    }
}
