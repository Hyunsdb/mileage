package com.triple.mileage.domain.review.exception;

import lombok.Getter;

import java.util.UUID;

@Getter
public class ReviewWriteTwiceException extends RuntimeException {
    private final UUID userId;

    public ReviewWriteTwiceException(UUID userId) {
        this.userId = userId;
    }

}
