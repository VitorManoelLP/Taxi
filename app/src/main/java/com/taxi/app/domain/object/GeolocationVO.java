package com.taxi.app.domain.object;

import java.util.stream.Stream;

import com.google.maps.model.GeocodingResult;

import lombok.NonNull;
import lombok.Value;

@Value
public class GeolocationVO {

    String placeId;
    double lat;
    double lng;
    String geoName;

    public GeolocationVO(@NonNull GeocodingResult[] geocodingResults) {
        this.placeId = getPlaceId(geocodingResults);
        this.lat = getLat(geocodingResults);
        this.lng = getLng(geocodingResults);
        this.geoName = getName(geocodingResults);
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
