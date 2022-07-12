package com.triple.mileage.domain.user.controller;

import com.triple.mileage.domain.user.dto.UserDto;
import com.triple.mileage.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UUID> addUser() {
        UUID uuid = userService.addUser();
        return new ResponseEntity<>(uuid, HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<UserDto> findUser(@PathVariable UUID uuid) {
        UserDto user = userService.findById(uuid);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
