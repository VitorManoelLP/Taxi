package com.taxi.app.application.usecase.persistence;

import com.taxi.app.domain.Coord;

public interface SaveCoord {
    void save(Coord coord);
}
