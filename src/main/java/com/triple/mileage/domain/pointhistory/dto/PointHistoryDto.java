package com.triple.mileage.domain.pointhistory.dto;

import com.triple.mileage.domain.event.domain.ActionType;
import com.triple.mileage.domain.event.domain.EventType;
import com.triple.mileage.domain.point.domain.Point;
import com.triple.mileage.domain.pointhistory.domain.PointHistory;
import lombok.Builder;

import java.util.UUID;

public class PointHistoryDto {
    private Point point;

    private ActionType actionType;

    private EventType eventType;

    private UUID reviewId;

    private UUID userId;

    private Integer pointCount;

    @Builder
    public PointHistoryDto(Point point, ActionType actionType, EventType eventType, UUID reviewId, UUID userId, Integer pointCount) {
        this.point = point;
        this.actionType = actionType;
        this.eventType = eventType;
        this.reviewId = reviewId;
        this.userId = userId;
        this.pointCount = pointCount;
    }

    public PointHistory toEntity() {
        return PointHistory.builder()
                .point(point)
                .pointCount(pointCount)
                .reviewId(reviewId)
                .eventType(eventType)
                .actionType(actionType)
                .build();
    }
}
