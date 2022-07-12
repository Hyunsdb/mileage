package com.triple.mileage.domain.review.dto;

import com.triple.mileage.domain.review.domain.Review;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
public class ReviewResponseDto {
    private Review review;
    private List<UUID> attachedPhotoIds;

    @Builder
    public ReviewResponseDto(Review review, List<UUID> attachedPhotoIds) {
        this.review = review;
        this.attachedPhotoIds = attachedPhotoIds;
    }
}