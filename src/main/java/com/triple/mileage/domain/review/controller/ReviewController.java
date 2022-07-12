package com.triple.mileage.domain.review.controller;

import com.triple.mileage.domain.review.dto.ReviewResponseDto;
import com.triple.mileage.domain.review.dto.ReviewSaveRequestDto;
import com.triple.mileage.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<ReviewResponseDto> addReview(ReviewSaveRequestDto reviewSaveRequestDto,
                                    @RequestParam("reviewImage") List<MultipartFile> reviewImage) throws Exception {
        ReviewResponseDto reviewResponseDto = reviewService.addReview(reviewSaveRequestDto, reviewImage);

        return new ResponseEntity<>(reviewResponseDto, HttpStatus.OK);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<ReviewResponseDto> updateReview(ReviewSaveRequestDto reviewSaveRequestDto,
                                       @RequestParam("reviewImage") List<MultipartFile> reviewImage,
                                       @PathVariable UUID reviewId) throws Exception {
        ReviewResponseDto reviewResponseDto = reviewService.updateReview(reviewId, reviewSaveRequestDto, reviewImage);

        return new ResponseEntity<>(reviewResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{reviewId}")
    public void deleteReview(@PathVariable UUID reviewId) {
        reviewService.deleteReview(reviewId);
    }
}
