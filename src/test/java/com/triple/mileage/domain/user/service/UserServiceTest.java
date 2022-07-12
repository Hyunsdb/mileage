package com.triple.mileage.domain.user.service;

import com.triple.mileage.domain.user.dto.UserDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    @DisplayName("사용자 등록")
    void addUser() {
        //given
        UUID savedUserId = userService.addUser();

        //when
        UserDto userDto = userService.findById(savedUserId);

        //then
        Assertions.assertEquals(savedUserId, userDto.getUuid());
    }
}