package com.triple.mileage.domain.point.service;

import com.triple.mileage.domain.event.domain.ActionType;
import com.triple.mileage.domain.event.domain.EventType;
import com.triple.mileage.domain.point.dto.PointDto;
import com.triple.mileage.domain.pointhistory.dto.PointHistoryDto;
import com.triple.mileage.domain.point.domain.Point;
import com.triple.mileage.domain.user.domain.User;
import com.triple.mileage.domain.point.repository.PointRepository;
import com.triple.mileage.domain.user.exception.UserNotFoundException;
import com.triple.mileage.domain.user.repository.UserRepository;
import com.triple.mileage.domain.pointhistory.service.PointHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class PointService {
    private final PointRepository pointRepository;
    private final UserRepository userRepository;
    private final PointHistoryService pointHistoryService;

    @Transactional
    public void initPoint(User user) {
        Point point = Point.builder()
                .pointCount(0)
                .user(user)
                .build();
        pointRepository.save(point);
    }

    @Transactional
    public PointDto getUserPoint(UUID userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        Point point = pointRepository.findByUser(user);

        return PointDto.builder()
                .user(point.getUser())
                .pointCount(point.getPointCount())
                .build();
    }
    @Transactional
    public void updatePoint(UUID userId, UUID reviewId, ActionType actionType, EventType eventType, int pointCount) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        Point point = pointRepository.findByUser(user); //해당 유저의 포인트 가져와서

        //포인트 update
        point.updatePointCount(pointCount);

        //히스토리 기록
        PointHistoryDto pointHistoryDto = PointHistoryDto.builder()
                .point(point)
                .userId(userId)
                .reviewId(reviewId)
                .pointCount(pointCount)
                .actionType(actionType)
                .eventType(eventType)
                .build();
        pointHistoryService.addPointHistory(pointHistoryDto);
    }
}
