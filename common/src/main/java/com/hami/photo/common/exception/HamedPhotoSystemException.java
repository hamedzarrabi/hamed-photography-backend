package com.hami.photo.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class HamedPhotoSystemException extends RuntimeException{

    @Getter
    private final HttpStatus status;
    private final String message;

    public HamedPhotoSystemException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HamedPhotoSystemException(String message, HttpStatus status, String message1) {
        super(message);
        this.status = status;
        this.message = message1;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
