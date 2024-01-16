package com.taxi.app.infra.usecase.distance;

import java.io.IOException;
import java.time.Instant;
import java.util.stream.Stream;

import com.google.maps.DistanceMatrixApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.DistanceMatrixElement;
import com.google.maps.model.TrafficModel;
import com.google.maps.model.TravelMode;
import com.taxi.app.application.usecase.CalculateDistance;
import com.taxi.app.domain.Coord;
import com.taxi.app.domain.Location;
import com.taxi.app.domain.object.CepVO;
import com.taxi.app.infra.usecase.RideCoordResolver;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CalculateDistanceGoogle implements CalculateDistance {

    private final GeoApiContext geoApiContext;
    private final RideCoordResolver rideCoordResolver;

    @Override
    public Location calculate(String from, String to) {

        try {

            final Coord coordFrom = rideCoordResolver.resolve(new CepVO(from));
            final Coord coordTo = rideCoordResolver.resolve(new CepVO(to));

            final DistanceMatrixApiRequest distanceMatrixApiRequest = DistanceMatrixApi.newRequest(geoApiContext)
                    .mode(TravelMode.DRIVING)
                    .destinations(String.format("place_id:%s", coordFrom.placeId()))
                    .origins(String.format("place_id:%s", coordTo.placeId()))
                    .departureTime(Instant.now())
                    .trafficModel(TrafficModel.BEST_GUESS);

            DistanceMatrix matrix = distanceMatrixApiRequest.await();

            final DistanceMatrixElement distanceMatrixElement = Stream.of(matrix.rows)
                    .map(row -> row.elements)
                    .flatMap(Stream::of)
                    .findFirst().orElseThrow(() -> new IllegalStateException("Matrix Element does not return a element"));

            final long distance = distanceMatrixElement.distance.inMeters;
            final String duration = distanceMatrixElement.duration.humanReadable;

            return new Location(coordFrom.coordName(), coordTo.coordName(), distance, duration);

        } catch (InterruptedException | ApiException | IOException | IllegalStateException e) {
            throw new IllegalStateException("It is not possible to calculate distance by cep", e);
        }

    }


}
