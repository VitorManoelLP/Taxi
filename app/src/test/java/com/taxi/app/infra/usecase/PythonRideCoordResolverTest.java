package com.taxi.app.infra.usecase;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.taxi.app.application.usecase.GeolocationCalculator;
import com.taxi.app.application.usecase.SaveCoord;
import com.taxi.app.domain.Coord;
import com.taxi.app.domain.object.CepVO;
import com.taxi.app.infra.repository.CoordRepository;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PythonRideCoordResolverTest {

    @Mock
    private GeolocationCalculator geolocationCalculator;

    @Mock
    private CoordRepository coordRepository;

    @Mock
    private SaveCoord saveCoord;

    @Test
    public void shouldCalculateNewCoord() {
        when(geolocationCalculator.calculate("87023060")).thenReturn(new Coord("1", "teste", -100.00, -120.00, ""));
        final RideCoordResolver pythonRidePriceResolver = new RideCoordResolver(geolocationCalculator, coordRepository, saveCoord);
        final Coord resolvedCood = pythonRidePriceResolver.resolve(new CepVO("87023060"));
        Assertions.assertThat(resolvedCood.coordName()).isEqualTo("teste");
        verify(geolocationCalculator, atLeastOnce()).calculate("87023060");
        verify(saveCoord, times(1)).save(any());
        verify(coordRepository, times(1)).findByCep(any());
    }

    @Test
    public void shouldSearchIfAlreadyExistsCoord() {
        when(coordRepository.findByCep(any())).thenReturn(Optional.of(new Coord("1", "teste", -100.00, -120.00, "")));
        final RideCoordResolver pythonRidePriceResolver = new RideCoordResolver(geolocationCalculator, coordRepository, saveCoord);
        final Coord resolvedCood = pythonRidePriceResolver.resolve(new CepVO("87023060"));
        Assertions.assertThat(resolvedCood.coordName()).isEqualTo("teste");
        verify(geolocationCalculator, never()).calculate("87023060");
        verify(saveCoord, never()).save(any());
        verify(coordRepository, times(1)).findByCep(any());
    }

}
