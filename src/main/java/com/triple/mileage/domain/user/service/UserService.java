package com.triple.mileage.domain.user.service;

import com.triple.mileage.domain.user.dto.UserDto;
import com.triple.mileage.domain.user.domain.User;
import com.triple.mileage.domain.user.exception.UserNotFoundException;
import com.triple.mileage.domain.user.repository.UserRepository;
import com.triple.mileage.domain.point.service.PointService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final PointService pointService;
    public UserDto findById(UUID uuid) {
        User user = userRepository.findById(uuid).orElseThrow(() -> new UserNotFoundException(uuid));

        return new UserDto(user);
    }

    public UUID addUser() {
        User savedUser = userRepository.save(new User());

        //포인트 초기화
        pointService.initPoint(savedUser);
        return savedUser.getUuid();
    }
}
