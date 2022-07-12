package com.triple.mileage.domain.point.controller;

import com.triple.mileage.domain.point.dto.PointDto;
import com.triple.mileage.domain.point.service.PointService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("points")
@RequiredArgsConstructor
public class PointController {
    private final PointService pointService;

    @GetMapping("/{userId}")
    public ResponseEntity<PointDto> getUserPoint(@PathVariable UUID userId) {
        PointDto userPoint = pointService.getUserPoint(userId);

        return new ResponseEntity<>(userPoint, HttpStatus.OK);
    }

}
