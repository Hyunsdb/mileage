package com.triple.mileage.domain.review.dto;

import com.triple.mileage.domain.place.domain.Place;
import com.triple.mileage.domain.review.domain.Review;
import com.triple.mileage.domain.user.domain.User;
import lombok.Builder;

import java.util.UUID;

public class ReviewSaveDto {
    private UUID uuid;
    private String content;
    private Place place;
    private User user;


    @Builder
    public ReviewSaveDto(UUID uuid, String content, Place place, User user) {
        this.uuid = uuid;
        this.content = content;
        this.place = place;
        this.user = user;
    }

    public Review toEntity() {
        return Review.builder()
                .uuid(uuid)
                .content(content)
                .place(place)
                .user(user)
                .build();
    }
}
