package com.atam.pruebatecnica.testatamtoken.application.exception;

import java.io.Serial;

public class ApiHttpClientErrorException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;
    private final int code = 401;
    private final String type = "ErrorType";

    public ApiHttpClientErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiHttpClientErrorException(String message) {
        super(message);
    }

    public ApiHttpClientErrorException() {
        super();
    }

}

