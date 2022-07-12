package com.triple.mileage.domain.image.dto;

import lombok.Builder;

import java.util.UUID;

public class ImageDto {
    private UUID uuid;

    private String imageName;

    private String originalImageName;

    private String imageUrl;

    @Builder
    public ImageDto(UUID uuid, String imageName, String originalImageName, String imageUrl) {
        this.uuid = uuid;
        this.imageName = imageName;
        this.originalImageName = originalImageName;
        this.imageUrl = imageUrl;
    }
}
