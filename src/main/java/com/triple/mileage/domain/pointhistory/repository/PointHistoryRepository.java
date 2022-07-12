package com.triple.mileage.domain.pointhistory.repository;

import com.triple.mileage.domain.pointhistory.domain.PointHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface PointHistoryRepository extends JpaRepository<PointHistory, Long> {
    // 해당 리뷰 아이디로 적립된 포인트 계산
    @Query(value = "select SUM (p.pointCount) from PointHistory as p group by :reviewId")
    Integer countByReviewId(UUID reviewId);
}
