package ru.amm.fileexplorer.server.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.BAD_REQUEST)
public class DirectoryAccessException extends RuntimeException {
    public DirectoryAccessException() {
        super();
    }

    public DirectoryAccessException(String message) {
        super(message);
    }

    public DirectoryAccessException(String message, Throwable cause) {
        super(message, cause);
    }

    public DirectoryAccessException(Throwable cause) {
        super(cause);
    }
}
