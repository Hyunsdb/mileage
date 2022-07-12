package com.triple.mileage.domain.user.dto;

import com.triple.mileage.domain.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class UserDto {
    private UUID uuid;

    @Builder
    public UserDto(User entity) {
        this.uuid = entity.getUuid();
    }

    public User toEntity() {
        return User.builder()
                .uuid(uuid)
                .build();
    }
}
