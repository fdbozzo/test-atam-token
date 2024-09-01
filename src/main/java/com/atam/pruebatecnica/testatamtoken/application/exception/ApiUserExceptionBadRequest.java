package com.atam.pruebatecnica.testatamtoken.application.exception;

import java.io.Serial;

public class ApiUserExceptionBadRequest extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;
    private final int code = 400;
    private final String type = "ErrorType";

    public ApiUserExceptionBadRequest(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiUserExceptionBadRequest(String message) {
        super(message);
    }

    public ApiUserExceptionBadRequest() {
        super();
    }

}

