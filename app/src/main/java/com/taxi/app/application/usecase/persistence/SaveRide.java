package com.taxi.app.application.usecase.persistence;

import com.taxi.app.domain.Ride;

public interface SaveRide {

    void save(Ride ride);

}
