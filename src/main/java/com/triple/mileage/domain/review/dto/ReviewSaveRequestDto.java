package com.triple.mileage.domain.review.dto;

import com.triple.mileage.domain.image.dto.ImageDto;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class ReviewSaveRequestDto {
    private String content;

    private List<ImageDto> reviewDtoList = new ArrayList<>();

    private UUID userId;

    private UUID placeId;

    private List<UUID> attachedPhotoIds = new ArrayList<>();
}

