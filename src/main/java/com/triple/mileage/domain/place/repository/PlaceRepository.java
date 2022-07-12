package com.triple.mileage.domain.place.repository;

import com.triple.mileage.domain.place.domain.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PlaceRepository extends JpaRepository<Place, UUID> {
}
