package com.triple.mileage.domain.point.service;

import com.triple.mileage.domain.point.domain.Point;
import com.triple.mileage.domain.point.repository.PointRepository;
import com.triple.mileage.domain.user.domain.User;
import com.triple.mileage.domain.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PointServiceTest {

    @Autowired
    PointService pointService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PointRepository pointRepository;

    @Test
    @DisplayName("포인트 초기화")
    void initPoint() {
        //given
        User user = new User();
        userRepository.save(user);

        //when
        pointService.initPoint(user);
        Point findUserPoint = pointRepository.findByUser(user);

        //then
        assertEquals(findUserPoint.getPointCount(),0);
    }
}