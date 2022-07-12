package com.triple.mileage.global.exception;

import com.triple.mileage.domain.place.exception.PlaceNotFoundException;
import com.triple.mileage.domain.review.exception.ReviewNotFoundException;
import com.triple.mileage.domain.review.exception.ReviewWriteTwiceException;
import com.triple.mileage.domain.user.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ResponseBody
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorResponse handleUserNotFoundException(UserNotFoundException e) {
        final ErrorCode errorCode = ErrorCode.USER_NOT_FOUND;
        log.error("ERROR : {} {}", errorCode.getMessage(), e.getUserId());
        return buildError(errorCode);
    }

    @ExceptionHandler(PlaceNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorResponse handlePlaceNotFoundException(PlaceNotFoundException e) {
        final ErrorCode errorCode = ErrorCode.PLACE_NOT_FOUND;
        log.error("ERROR : {} {}", errorCode.getMessage(), e.getPlaceId());
        return buildError(errorCode);
    }

    @ExceptionHandler(ReviewNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorResponse handleReviewNotFoundException(ReviewNotFoundException e) {
        final ErrorCode errorCode = ErrorCode.REVIEW_NOT_FOUND;
        log.error("ERROR : {} {}", errorCode.getMessage(), e.getReviewId());
        return buildError(errorCode);
    }

    @ExceptionHandler(ReviewWriteTwiceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorResponse handleReviewWriteTwiceException(ReviewWriteTwiceException e) {
        final ErrorCode errorCode = ErrorCode.REVIEW_WRITE_TWICE;
        log.error("ERROR : {} {}", errorCode.getMessage(), e.getUserId());
        return buildError(errorCode);
    }

    private ErrorResponse buildError(ErrorCode errorCode) {
        return ErrorResponse.builder()
                .code(errorCode.getCode())
                .status(errorCode.getStatus())
                .message(errorCode.getMessage())
                .build();
    }

}
