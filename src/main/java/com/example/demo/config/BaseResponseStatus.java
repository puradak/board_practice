package com.example.demo.config;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 에러 코드 관리
 */
@Getter
public enum BaseResponseStatus {
    /**
     * 200 : 요청 성공
     */
    SUCCESS(true, HttpStatus.OK.value(), "요청에 성공하였습니다."),


    /**
     * 400 : Request 오류, Response 오류
     */
    // Common

    REQUEST_ERROR(false, HttpStatus.BAD_REQUEST.value(), "입력값을 확인해주세요."),
    EMPTY_JWT(false, HttpStatus.UNAUTHORIZED.value(), "JWT를 입력해주세요."),
    INVALID_JWT(false, HttpStatus.UNAUTHORIZED.value(), "유효하지 않은 JWT입니다."),
    INVALID_USER_JWT(false,HttpStatus.FORBIDDEN.value(),"권한이 없는 유저의 접근입니다."),
    RESPONSE_ERROR(false, HttpStatus.NOT_FOUND.value(), "값을 불러오는데 실패하였습니다."),


    /**
    * 400x : User Request 오류
    */
    // users
    USERS_EMPTY_USER_ID(false, HttpStatus.BAD_REQUEST.value(), "유저 아이디 값을 확인해주세요."),

    // [POST] /users
    POST_USERS_EMPTY_EMAIL(false, 4000, "이메일을 입력해주세요."),
    POST_USERS_INVALID_EMAIL(false, 4001, "이메일 형식을 확인해주세요."),
    POST_USERS_EXISTS_EMAIL(false,4002,"중복된 이메일입니다."),

    FAILED_TO_LOGIN(false,4003,"없는 아이디거나 비밀번호가 틀렸습니다."),

    /**
     * 401x : Board Request 오류
     */
    // [POST] /board
    POST_BOARD_EXISTS_BOARD(false,4010, "이미 존재하는 게시판입니다."),

    /**
     * 402x : Post Requset 오류
     */
    // [POST] /post
    NON_EXIST_BOARDIDX(false, 4020, "유효하지 않은 게시판 IDX입니다."),
    EMPTY_POST_TITLE(false, 4021, "유효하지 않은 게시판 제목입니다."),
    EMPTY_POST_CONTENTS(false, 4022, "유효하지 않은 게시판 내용입니다."),
    NOT_EXIST_POST_CONTENTS(false, 4023, "해당하는 게시글이 없습니다."),

    /**
     * 403x : Reply Request 오류
     */
    // [POST] /reply
    NOT_EXIST_REPLY_IDX(false, 4030, "존재하지 않는 댓글 IDX 입니다."),

    /**
     * 50 : Database, Server 오류
     */
    DATABASE_ERROR(false, HttpStatus.INTERNAL_SERVER_ERROR.value(), "데이터베이스 연결에 실패하였습니다."),
    SERVER_ERROR(false, HttpStatus.INTERNAL_SERVER_ERROR.value(), "서버와의 연결에 실패하였습니다."),

    //[PATCH] /users/{userIdx}
    MODIFY_FAIL_USERNAME(false,HttpStatus.INTERNAL_SERVER_ERROR.value(),"유저네임 수정 실패"),

    PASSWORD_ENCRYPTION_ERROR(false, HttpStatus.INTERNAL_SERVER_ERROR.value(), "비밀번호 암호화에 실패하였습니다."),
    PASSWORD_DECRYPTION_ERROR(false, HttpStatus.INTERNAL_SERVER_ERROR.value(), "비밀번호 복호화에 실패하였습니다.");


    private final boolean isSuccess;
    private final int code;
    private final String message;

    private BaseResponseStatus(boolean isSuccess, int code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}
