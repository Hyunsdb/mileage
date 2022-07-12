package com.triple.mileage.domain.place.exception;

import lombok.Getter;

import java.util.UUID;

@Getter
public class PlaceNotFoundException extends RuntimeException {

    private final UUID placeId;

    public PlaceNotFoundException(UUID placeId) {
        this.placeId = placeId;
    }
}
