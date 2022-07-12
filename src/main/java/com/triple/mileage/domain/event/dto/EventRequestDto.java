package com.triple.mileage.domain.event.dto;

import com.triple.mileage.domain.event.domain.ActionType;
import com.triple.mileage.domain.event.domain.EventType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class EventRequestDto {

    private EventType type;

    private ActionType action;

    private UUID reviewId;

    private String content;

    private List<UUID> attachedPhotoIds;

    private UUID userId;

    private UUID placeId;
}
