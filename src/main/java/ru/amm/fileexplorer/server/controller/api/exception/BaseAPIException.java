package ru.amm.fileexplorer.server.controller.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BaseAPIException extends RuntimeException {
    public BaseAPIException() {
    }

    public BaseAPIException(String message) {
        super(message);
    }

    public BaseAPIException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseAPIException(Throwable cause) {
        super(cause);
    }
}
