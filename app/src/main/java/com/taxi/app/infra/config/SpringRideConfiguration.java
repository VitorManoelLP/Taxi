package com.taxi.app.infra.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.maps.GeoApiContext;
import com.taxi.app.application.usecase.distance.CalculateDistance;
import com.taxi.app.application.usecase.distance.GeolocationCalculator;
import com.taxi.app.infra.usecase.RideCoordResolver;
import com.taxi.app.infra.usecase.distance.CalculateDistanceGoogle;
import com.taxi.app.infra.usecase.distance.GoogleGeolocationCalculator;

@Configuration
@ConditionalOnProperty(name = "use-google-calculator", havingValue = "true")
public class SpringRideConfiguration {

    @Value("${google.key}")
    private String key;

    @Bean
    public GeoApiContext geoApiContext() {
        return new GeoApiContext.Builder()
                .apiKey(key)
                .build();
    }

    @Bean
    public CalculateDistance calculateDistance(GeoApiContext geoApiContext, RideCoordResolver rideCoordResolver) {
        return new CalculateDistanceGoogle(geoApiContext, rideCoordResolver);
    }

    @Bean
    public GeolocationCalculator geolocationCalculator(GeoApiContext geoApiContext) {
        return new GoogleGeolocationCalculator(geoApiContext);
    }

}
