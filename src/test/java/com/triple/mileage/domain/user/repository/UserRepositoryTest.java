package com.triple.mileage.domain.user.repository;

import com.triple.mileage.domain.user.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.NoSuchElementException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("회원 등록")
    public void createUser() {
        //given
        User user = new User();

        //when
        UUID userId = userRepository.save(user).getUuid();

        //then
        UUID savedUserId = userRepository.findById(userId).get().getUuid();
        assertEquals(user.getUuid(),savedUserId);
    }


    @Test()
    @DisplayName("회원 삭제")
    public void deleteUser() {
        //given
        User user = new User();

        //when3
        UUID userId = userRepository.save(user).getUuid();
        userRepository.deleteById(userId);

        //then
        assertThrows(NoSuchElementException.class, () -> userRepository.findById(userId).get());
    }
}