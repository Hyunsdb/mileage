package com.triple.mileage.global.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    USER_NOT_FOUND(400, "U001", "사용자를 찾지 못했습니다."),


    PLACE_NOT_FOUND(400, "P001", "해당 장소를 찾지 못했습니다."),


    REVIEW_NOT_FOUND(400, "R001", "해당 리뷰를 찾지 못했습니다."),
    REVIEW_WRITE_TWICE(400, "R002", "같은 장소에 리뷰를 두 번 쓸 수 없습니다.");


    private final String code;
    private final String message;
    private final int status;

    ErrorCode(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
