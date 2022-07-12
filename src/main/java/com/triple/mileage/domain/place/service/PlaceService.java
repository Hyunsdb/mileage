package com.triple.mileage.domain.place.service;

import com.triple.mileage.domain.place.dto.PlaceDto;
import com.triple.mileage.domain.place.domain.Place;
import com.triple.mileage.domain.place.exception.PlaceNotFoundException;
import com.triple.mileage.domain.place.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class PlaceService {
    private final PlaceRepository placeRepository;

    public PlaceDto findById(UUID id) {
        Place place = placeRepository.findById(id)
                .orElseThrow(() -> new PlaceNotFoundException(id));

        return new PlaceDto(place);
    }


    public UUID addPlace() {
        Place place = new Place();
        Place savedPlace = placeRepository.save(place);

        return savedPlace.getUuid();
    }
}
