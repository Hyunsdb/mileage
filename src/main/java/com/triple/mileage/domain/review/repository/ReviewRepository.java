package com.triple.mileage.domain.review.repository;

import com.triple.mileage.domain.place.domain.Place;
import com.triple.mileage.domain.review.domain.Review;
import com.triple.mileage.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReviewRepository extends JpaRepository<Review, UUID> {
    boolean existsByPlace(Place place);

    boolean existsByPlaceAndUser(Place place, User user);
}
