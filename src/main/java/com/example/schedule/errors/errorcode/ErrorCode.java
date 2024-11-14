package com.example.schedule.errors.errorcode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    /* 400 BAD_REQUEST : 잘못된 요청 */


    /* 401 UNAUTHORIZED : 인증되지 않은 사용자 */
    UNAUTHORIZED_PASSWORD(UNAUTHORIZED, "password 가 일치하지 않습니다."),
    UNAUTHORIZED_USER(UNAUTHORIZED, "권한이 없습니다. 작성자만 가능합니다."),

    /* 404 NOT_FOUND : Resource 를 찾을 수 없음 */
    USER_NOT_FOUND(NOT_FOUND, "해당 id 로 인한 유저정보를 찾을 수 없습니다"),
    USERNAME_NOT_FOUND(NOT_FOUND , "해당 username 으로 인한 유저정보를 찾을 수 없습니다"),
    SCHEDULE_NOT_FOUND(NOT_FOUND , "해당 id 로 인한 일정정보를 찾을 수 없습니다"),
    COMMENT_NOT_FOUND(NOT_FOUND , "해당 id 로 인한 댓글정보를 찾을 수 없습니다"),

    /* 409 CONFLICT : Resource 의 현재 상태와 충돌. 보통 중복된 데이터 존재 */
    DUPLICATE_RESOURCE(CONFLICT, "데이터가 이미 존재합니다"),

    ;

    private final HttpStatus httpStatus;
    private final String detail;
}
