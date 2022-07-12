package com.triple.mileage.domain.review.domain;

import com.triple.mileage.domain.user.domain.User;
import com.triple.mileage.domain.review.dto.ReviewSaveRequestDto;
import com.triple.mileage.domain.base.BaseEntity;
import com.triple.mileage.domain.place.domain.Place;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
public class Review extends BaseEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(columnDefinition = "BINARY(16)")
    private UUID uuid;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    private Place place;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Review(UUID uuid, String content, Place place, User user) {
        this.uuid = uuid;
        this.content = content;
        this.place = place;
        this.user = user;
    }

    public void updateReview(ReviewSaveRequestDto reviewSaveRequestDto) {
        this.content = reviewSaveRequestDto.getContent();

    }
}
