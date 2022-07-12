package com.triple.mileage.domain.point.domain;

import com.triple.mileage.domain.user.domain.User;
import com.triple.mileage.domain.base.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Point extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    private Integer pointCount;


    @Builder
    public Point(User user, Integer pointCount) {
        this.user = user;
        this.pointCount = pointCount;
    }


    public Point updatePointCount(Integer count) {
        this.pointCount = this.pointCount + count;

        return this;
    }
}
