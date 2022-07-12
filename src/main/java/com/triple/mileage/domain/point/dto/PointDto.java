package com.triple.mileage.domain.point.dto;

import com.triple.mileage.domain.point.domain.Point;
import com.triple.mileage.domain.user.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PointDto {

    private User user;

    private Integer pointCount;


    @Builder
    public PointDto(User user, Integer pointCount) {
        this.user = user;
        this.pointCount = pointCount;
    }

    public Point toEntity() {
        return Point.builder()
                .user(user)
                .pointCount(pointCount)
                .build();
    }
}