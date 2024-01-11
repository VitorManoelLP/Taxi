package com.taxi.app.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public enum RideStatus {

    NOT_STARTED(1L, "Not Started"),
    STARTED(2L, "Started"),
    CANCELED(3L , "Canceled"),
    COMPLETED(4L, "Completed");

    private final Long id;
    private final String description;

    @JsonValue
    public long getId() {
        return id;
    }

    @JsonCreator
    public static RideStatus getType(long typeId) {
        List<RideStatus> rideStatus = List.of(RideStatus.NOT_STARTED, RideStatus.STARTED, RideStatus.CANCELED, RideStatus.COMPLETED);
        return rideStatus.stream().filter(type -> type.getId() == typeId).findFirst().orElse(null);
    }

}
