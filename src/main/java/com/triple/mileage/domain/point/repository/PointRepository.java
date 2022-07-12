package com.triple.mileage.domain.point.repository;

import com.triple.mileage.domain.point.domain.Point;
import com.triple.mileage.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointRepository extends JpaRepository<Point, Long> {
    Point findByUser(User user);
}
