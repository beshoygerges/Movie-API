package com.movie.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class MovieAPIException {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public static final class MovieNotFoundException extends BaseException {

        public MovieNotFoundException(String message) {
            super(message);
        }
    }
}
