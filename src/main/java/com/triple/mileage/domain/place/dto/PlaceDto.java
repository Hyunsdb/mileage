package com.triple.mileage.domain.place.dto;

import com.triple.mileage.domain.place.domain.Place;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class PlaceDto {
    private UUID uuid;

    public PlaceDto(Place entity) {
        this.uuid = entity.getUuid();
    }

    public Place toEntity() {
        return Place.builder()
                .uuid(uuid)
                .build();
    }
}
