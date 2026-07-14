package com.example.demo.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

    STUDENT_NOT_FOUND(1001, "Student not found", HttpStatus.NOT_FOUND),
    EMAIL_ALREADY_EXISTS(1002, "Email already exists", HttpStatus.CONFLICT),
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR);

    private final int code;
    private final String message;
    private final HttpStatus httpStatus;

    ErrorCode(int code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}