package com.triple.mileage.domain.review.service;

import com.triple.mileage.domain.place.dto.PlaceDto;
import com.triple.mileage.domain.review.dto.ReviewResponseDto;
import com.triple.mileage.domain.review.dto.ReviewSaveDto;
import com.triple.mileage.domain.review.dto.ReviewSaveRequestDto;
import com.triple.mileage.domain.review.exception.ReviewWriteTwiceException;
import com.triple.mileage.domain.user.dto.UserDto;
import com.triple.mileage.domain.image.domain.Image;
import com.triple.mileage.domain.review.domain.Review;
import com.triple.mileage.domain.review.repository.ReviewRepository;
import com.triple.mileage.domain.user.service.UserService;
import com.triple.mileage.domain.image.service.ImageService;
import com.triple.mileage.domain.place.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final PlaceService placeService;
    private final UserService userService;
    private final ImageService imageService;

    public ReviewResponseDto addReview(ReviewSaveRequestDto reviewSaveRequestDto, List<MultipartFile> reviewImageList) throws Exception {
        PlaceDto placeDto = placeService.findById(reviewSaveRequestDto.getPlaceId());
        UserDto user = userService.findById(reviewSaveRequestDto.getUserId());

        //한 장소에 한 리뷰만 남길 수 있음
        if (reviewRepository.existsByPlaceAndUser(placeDto.toEntity(), user.toEntity())) {
            throw new ReviewWriteTwiceException(user.getUuid());
        }

        //DTO 생성 및 리뷰 저장
        Review review = ReviewSaveDto.builder()
                .content(reviewSaveRequestDto.getContent())
                .place(placeDto.toEntity())
                .user(user.toEntity())
                .build()
                .toEntity();

        Review savedReview = reviewRepository.save(review);

        //이미지 저장
        List<UUID> imageIdList = addReviewImage(reviewImageList, savedReview);

        return ReviewResponseDto.builder()
                .attachedPhotoIds(imageIdList)
                .review(savedReview)
                .build();
    }

    //리뷰 수정
    public ReviewResponseDto updateReview(UUID reviewId, ReviewSaveRequestDto reviewSaveRequestDto, List<MultipartFile> reviewImageList) throws Exception {
        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new EntityNotFoundException("해당 리뷰가 없습니다"));
        review.updateReview(reviewSaveRequestDto);

        //기존 이미지 파일 삭제
        imageService.deleteImages(review);

        //새 이미지 등록
        List<UUID> imageIdList = addReviewImage(reviewImageList, review);
        return ReviewResponseDto.builder()
                .attachedPhotoIds(imageIdList)
                .review(review)
                .build();
    }

    public void deleteReview(UUID reviewId) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new EntityNotFoundException("해당 리뷰가 없습니다"));
        reviewRepository.delete(review);
    }

    private List<UUID> addReviewImage(List<MultipartFile> reviewImageList, Review review) throws Exception {
        List<UUID> imageIdList = new ArrayList<>();

        //이미지 등록된 게 있을 때
        if (!reviewImageList.get(0).isEmpty()) {
            for (MultipartFile multipartFile : reviewImageList) {
                Image image = Image.builder().review(review).build();

                imageService.addImages(image, multipartFile);
                imageIdList.add(image.getUuid());
            }
        }

        return imageIdList;
    }

}
