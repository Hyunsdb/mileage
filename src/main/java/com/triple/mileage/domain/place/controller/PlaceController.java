package com.triple.mileage.domain.place.controller;

import com.triple.mileage.domain.place.dto.PlaceDto;
import com.triple.mileage.domain.place.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("places")
@RequiredArgsConstructor
public class PlaceController {

    private final PlaceService placeService;

    @PostMapping
    public ResponseEntity<UUID> addPlace() {
        UUID uuid = placeService.addPlace();

        return new ResponseEntity<>(uuid, HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<PlaceDto> findPlace(@PathVariable UUID uuid) {
        PlaceDto place = placeService.findById(uuid);

        return new ResponseEntity<>(place, HttpStatus.OK);
    }
}
