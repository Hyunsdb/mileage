package com.triple.mileage.domain.event.service;

import com.triple.mileage.domain.place.exception.PlaceNotFoundException;
import com.triple.mileage.domain.point.service.PointService;
import com.triple.mileage.domain.event.dto.EventRequestDto;
import com.triple.mileage.domain.place.domain.Place;
import com.triple.mileage.domain.review.domain.Review;
import com.triple.mileage.domain.image.repository.ImageRepository;
import com.triple.mileage.domain.place.repository.PlaceRepository;
import com.triple.mileage.domain.pointhistory.repository.PointHistoryRepository;
import com.triple.mileage.domain.review.exception.ReviewNotFoundException;
import com.triple.mileage.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;


@Service
@RequiredArgsConstructor
@Transactional
public class EventService {
    private final ReviewRepository reviewRepository;
    private final ImageRepository imageRepository;
    private final PlaceRepository placeRepository;
    private final PointService pointService;

    private final PointHistoryRepository pointHistoryRepository;

    public void addReviewEvent(EventRequestDto eventRequestDto) {
        Place place = placeRepository.findById(eventRequestDto.getPlaceId())
                .orElseThrow(() -> new PlaceNotFoundException(eventRequestDto.getPlaceId()));


        //이벤트가 포인트 적립에 적합하는지 확인
        int pointCount = calculateAddPoint(eventRequestDto, place);
        if (pointCount > 0) {
            pointService.updatePoint(eventRequestDto.getUserId(),
                    eventRequestDto.getReviewId(),
                    eventRequestDto.getAction(),
                    eventRequestDto.getType(),
                    pointCount);
        }
    }

    public void modReviewEvent(EventRequestDto eventRequestDto) {
        Review review = reviewRepository.findById(eventRequestDto.getReviewId())
                .orElseThrow(() -> new ReviewNotFoundException(eventRequestDto.getReviewId()));
        //포인트 계산
        int pointCount = calculateModPoint(eventRequestDto, review);
        if (pointCount != 0) {
            pointService.updatePoint(eventRequestDto.getUserId(),
                    eventRequestDto.getReviewId(),
                    eventRequestDto.getAction(),
                    eventRequestDto.getType(),
                    pointCount);
        }
    }

    public void deleteReviewEvent(EventRequestDto eventRequestDto) {
        int pointCount = calculateDeletePoint(eventRequestDto.getReviewId());
        if (pointCount > 0) {
            pointService.updatePoint(eventRequestDto.getUserId(),
                    eventRequestDto.getReviewId(),
                    eventRequestDto.getAction(),
                    eventRequestDto.getType(),
                    -pointCount);
        }
    }

    public int calculateAddPoint(EventRequestDto eventRequestDto, Place place) {
        int point = 0;

        // 1자 이상 텍스트 작성
        if (eventRequestDto.getContent().length() > 0) {
            ++point;
        }

        // 1장 이상 사진 첨부
        if (eventRequestDto.getAttachedPhotoIds().size() > 0) {
            ++point;
        }

        //첫 리뷰 작성 1점
        if (reviewRepository.existsByPlace(place)) {
            ++point;
        }
        return point;
    }

    public int calculateModPoint(EventRequestDto eventRequestDto, Review review) {
        int point = 0;
        int savedImageCount = imageRepository.countByReview(review);
        int savedContentLength = review.getContent().length();
        int attachedPhotoCount = eventRequestDto.getAttachedPhotoIds().size();

        //작성된 내용을 지우면 -1
        if (savedContentLength > 0 && eventRequestDto.getContent().length() == 0) {
            --point;
        }

        //작성된 내용이 추가되면 +1
        if (savedContentLength == 0 && eventRequestDto.getContent().length() > 0) {
            ++point;
        }

        //사진을 등록하지 않았다가 추가로 등록하는 경우 +1
        if (savedImageCount == 0 && attachedPhotoCount > 0) {
            ++point;
        }

        //사진을 등록했다가 지우는 경우 -1
        if (savedImageCount > 0 && attachedPhotoCount == 0) {
            --point;
        }
        return point;
    }

    public int calculateDeletePoint(UUID reviewId) {
        //기존 리뷰에서 얼만큼 얻었나 확인
        return pointHistoryRepository.countByReviewId(reviewId);
    }
}