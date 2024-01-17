package com.taxi.app.infra.usecase.distance;

import java.io.IOException;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.GeocodingApiRequest;
import com.google.maps.errors.ApiException;
import com.taxi.app.application.usecase.GeolocationCalculator;
import com.taxi.app.domain.Coord;
import com.taxi.app.domain.object.GeolocationVO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GoogleGeolocationCalculator implements GeolocationCalculator {

    private final GeoApiContext geoApiContext;

    @Override
    public Coord calculate(String cep) {
        try {
            final GeocodingApiRequest geocodingApiRequest = GeocodingApi.newRequest(geoApiContext).address(cep);
            final GeolocationVO geolocation = new GeolocationVO(geocodingApiRequest.await());
            return new Coord(cep, geolocation.getGeoName(), geolocation.getLat(), geolocation.getLng(), geolocation.getPlaceId());
        } catch (IOException | InterruptedException | ApiException e) {
            throw new IllegalStateException("It is not possible to calculate location coord by cep");
        }
    }


}
