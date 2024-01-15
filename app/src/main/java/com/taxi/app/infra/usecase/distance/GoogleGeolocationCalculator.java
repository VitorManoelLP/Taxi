package com.taxi.app.infra.usecase.distance;

import java.io.IOException;
import java.util.stream.Stream;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.GeocodingApiRequest;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.taxi.app.application.usecase.distance.GeolocationCalculator;
import com.taxi.app.domain.Coord;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GoogleGeolocationCalculator implements GeolocationCalculator {

    private final GeoApiContext geoApiContext;

    @Override
    public Coord calculate(String cep) {

        try {

            final GeocodingApiRequest geocodingApiRequest = GeocodingApi.newRequest(geoApiContext).address(cep);
            final GeocodingResult[] geocodingResults = geocodingApiRequest.await();
            final String placeId = getPlaceId(geocodingResults);
            final double lat = getLat(geocodingResults);
            final double lng = getLng(geocodingResults);
            final String name = getName(geocodingResults);
            return new Coord(cep, name, lat, lng, placeId);

        } catch (IOException | InterruptedException | ApiException e) {
            throw new IllegalStateException("It is not possible to calculate location coord by cep");
        }
    }

    private double getLat(GeocodingResult[] resultFrom) {
        return Stream.of(resultFrom)
                .map(geocodingResult -> geocodingResult.geometry.location.lat)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("It is not possible to calculate latitude coord by cep"));
    }

    private double getLng(GeocodingResult[] resultFrom) {
        return Stream.of(resultFrom)
                .map(geocodingResult -> geocodingResult.geometry.location.lng)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("It is not possible to calculate longitude coord by cep"));
    }

    private String getPlaceId(GeocodingResult[] resultFrom) {
        return Stream.of(resultFrom)
                .map(geocodingResult -> geocodingResult.placeId)
                .findFirst()
                .orElse("");
    }

    private String getName(GeocodingResult[] resultFrom) {
        return Stream.of(resultFrom)
                .map(geocodingResult -> geocodingResult.formattedAddress)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("It it not possible to get coord name by cep"));
    }
}
