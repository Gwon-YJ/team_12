package com.example.newsfeed.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorType {

    // DB 조회 실패
    ENTITY_NOT_FOUND(HttpStatus.NOT_FOUND, "데이터를 찾을 수 없습니다."),//DB 조회 실패
    // @VALID 실패
    METHOD_ARGUMENT_NOT_VALID(HttpStatus.BAD_REQUEST, "형식을 준수해서 입력해야 합니다."),
    // 인가 실패
    ACCESS_DENIED(HttpStatus.FORBIDDEN, "접근 권한이 없습니다."),
    // 인증 실패: 로그인 시 비밀번호, 이메일 불일치
    PASSWORD_EMAIL_MISMATCH(HttpStatus.UNAUTHORIZED, "비밀번호나 이메일이 일치하지 않습니다."),
    // 토큰 만료
    TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "토큰이 만료되었습니다."),
    // 비밀번호 변경 시 불일치
    PASSWORD_MISMATCH(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다."),
    // 리소스 중복
    RESOURCE_ALREADY_EXIST(HttpStatus.BAD_REQUEST, "이미 등록된 이메일입니다."),
    // 자기자신에 대한 팔로잉 팔로우
    INAPPROPRIATE_APPROACH(HttpStatus.BAD_REQUEST, "비정상적인 접근입니다."),
    // 이미 완료된 작업 중복 시행: 팔로우 완료 후 다시 팔로우 하려는 경우
    DUPLICATE_TASK(HttpStatus.BAD_REQUEST, "이미 완료된 작업입니다.");

    private final HttpStatus httpStatus;
    private final String errorMessage;

    ErrorType(HttpStatus httpStatus, String errorMessage) {
        this.httpStatus = httpStatus;
        this.errorMessage = errorMessage;
    }
}
