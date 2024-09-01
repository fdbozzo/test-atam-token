package com.atam.pruebatecnica.testatamtoken.application.exception;

import com.atam.pruebatecnica.testatamtoken.application.model.generated.HttpResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.UnknownContentTypeException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.List;
import java.util.Objects;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionController {

    @ExceptionHandler({HttpClientErrorException.class, ApiHttpClientErrorException.class})
    public ResponseEntity<HttpResponse> handleHttpClientErrorException(Exception e) {
        log.error("handleHttpClientErrorException: message={}", e.getMessage(), e);
        var httpStatusError = HttpStatus.UNAUTHORIZED;
        var response = new HttpResponse()
                .code(httpStatusError.value())
                .type(httpStatusError.name())
                .message(e.getMessage());
        return new ResponseEntity<>(response, httpStatusError);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<HttpResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("handleMethodArgumentNotValidException: message={}", e.getMessage(), e);
        var httpStatusError = HttpStatus.BAD_REQUEST;
        var response = new HttpResponse()
                .code(httpStatusError.value())
                .type(httpStatusError.name())
                .message(e.getMessage());
        return new ResponseEntity<>(response, httpStatusError);
    }

    @ExceptionHandler({UnknownContentTypeException.class, HttpMediaTypeNotSupportedException.class})
    public ResponseEntity<HttpResponse> handleContentTypeException(Exception e) {
        log.error("handleContentTypeException: message={}", e.getMessage(), e);
        var httpStatusError = HttpStatus.BAD_REQUEST;
        var response = new HttpResponse()
                .code(httpStatusError.value())
                .type(httpStatusError.name())
                .message(e.getMessage());
        return new ResponseEntity<>(response, httpStatusError);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<HttpResponse> handleNoResourceFoundException(NoResourceFoundException e) {
        log.error("NoResourceFoundException: {}", e.getMessage(), e);
        var httpStatusError = HttpStatus.NOT_FOUND;
        var response = new HttpResponse()
                .code(httpStatusError.value())
                .type(httpStatusError.name())
                .message(e.getMessage() + " / Check URI endpoint spelling");
        return new ResponseEntity<>(response, httpStatusError);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<List<String>> handleResponseStatusException(ResponseStatusException e) {
        log.error("ResponseStatusException: {}", e.getReason(), e);
        return new ResponseEntity<>(List.of(e.getMessage(), Objects.requireNonNull(e.getReason())), e.getStatusCode());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<HttpResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.error("HttpMessageNotReadableException: {}", e.getMessage(), e);
        var httpStatusError = HttpStatus.BAD_REQUEST;
        var response = new HttpResponse()
                .code(httpStatusError.value())
                .type(httpStatusError.name())
                .message(e.getMessage());
        return new ResponseEntity<>(response, httpStatusError);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<HttpResponse> handleException(Exception e) {
        log.error("Exception: {}", e.getMessage(), e);
        var httpStatusError = HttpStatus.INTERNAL_SERVER_ERROR;
        var response = new HttpResponse()
                .code(httpStatusError.value())
                .type(httpStatusError.name())
                .message(e.getMessage());
        return new ResponseEntity<>(response, httpStatusError);
    }

}
