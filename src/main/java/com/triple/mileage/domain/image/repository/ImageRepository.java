package com.triple.mileage.domain.image.repository;

import com.triple.mileage.domain.image.domain.Image;
import com.triple.mileage.domain.review.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ImageRepository extends JpaRepository<Image, UUID> {
    List<Image> findByReview(Review review);

    //    @Query("select i from Image i where i.review.uuid = :reviewId")
    Integer countByReview(Review review);
}
