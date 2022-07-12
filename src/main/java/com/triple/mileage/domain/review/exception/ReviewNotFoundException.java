package com.triple.mileage.domain.review.exception;

import lombok.Getter;

import java.util.UUID;

@Getter
public class ReviewNotFoundException extends RuntimeException {

    private final UUID reviewId;

    public ReviewNotFoundException(UUID reviewId) {
        this.reviewId = reviewId;
    }
}