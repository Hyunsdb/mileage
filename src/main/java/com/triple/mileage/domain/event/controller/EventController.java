package com.triple.mileage.domain.event.controller;

import com.triple.mileage.domain.event.service.EventService;
import com.triple.mileage.domain.event.dto.EventRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("events")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @PostMapping
    public ResponseEntity<String> event(@RequestBody EventRequestDto eventDto) {
        switch (eventDto.getAction()) {
            case ADD:
                eventService.addReviewEvent(eventDto);
                return new ResponseEntity<>(HttpStatus.OK);
            case MOD:
                eventService.modReviewEvent(eventDto);
                return new ResponseEntity<>(HttpStatus.OK);
            case DELETE:
                eventService.deleteReviewEvent(eventDto);
                return new ResponseEntity<>(HttpStatus.OK);
            default:
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
