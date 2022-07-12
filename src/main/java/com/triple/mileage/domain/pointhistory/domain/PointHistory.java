package com.triple.mileage.domain.pointhistory.domain;

import com.triple.mileage.domain.event.domain.ActionType;
import com.triple.mileage.domain.event.domain.EventType;
import com.triple.mileage.domain.point.domain.Point;
import com.triple.mileage.domain.base.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
public class PointHistory extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "point_id")
    private Point point;

    @Enumerated(value = EnumType.STRING)
    private ActionType actionType;

    @Enumerated(value = EnumType.STRING)
    private EventType eventType;

    private UUID reviewId;

    private Integer pointCount;

    @Builder
    public PointHistory(Point point, ActionType actionType, EventType eventType, UUID reviewId, Integer pointCount) {
        this.point = point;
        this.actionType = actionType;
        this.eventType = eventType;
        this.reviewId = reviewId;
        this.pointCount = pointCount;
    }
}
