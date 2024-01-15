package com.taxi.app.infra.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.taxi.app.application.usecase.distance.CalculateDistance;
import com.taxi.app.application.usecase.distance.GeolocationCalculator;
import com.taxi.app.infra.clients.GeolocationPythonClient;
import com.taxi.app.infra.usecase.RideCoordResolver;
import com.taxi.app.infra.usecase.distance.CalculateDistanceHaversine;
import com.taxi.app.infra.usecase.distance.PythonGeolocationCalculator;

@Configuration
@ConditionalOnProperty(name = "google.enable", havingValue = "false", matchIfMissing = true)
public class SpringRideLocaleConfiguration {

    @Bean
    public CalculateDistance calculateDistance(RideCoordResolver ridePriceResolver) {
        return new CalculateDistanceHaversine(ridePriceResolver);
    }

    @Bean
    public GeolocationCalculator geolocationCalculator(GeolocationPythonClient geolocationPythonClient) {
        return new PythonGeolocationCalculator(geolocationPythonClient);
    }
}
