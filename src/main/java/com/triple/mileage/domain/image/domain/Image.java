package com.triple.mileage.domain.image.domain;

import com.triple.mileage.domain.review.domain.Review;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
public class Image {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(columnDefinition = "BINARY(16)")
    private UUID uuid;

    private String imageName; //이미지 파일명

    private String originalName; //원본 이미지 파일명

    private String imageUrl; //이미지 조회 경로

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private Review review;

    @Builder
    public Image(UUID uuid, Review review) {
        this.uuid = uuid;
        this.review = review;
    }

    public void updateImage(String imageName, String originalName, String imageUrl){
        this.imageName = imageName;
        this.originalName = originalName;
        this.imageUrl = imageUrl;
    }
}
