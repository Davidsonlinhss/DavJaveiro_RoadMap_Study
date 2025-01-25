package com.davjaveiro.eCommerceApp.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Locale;

@ControllerAdvice
public class RestApiErrorHandler {
    private final MessageSource messageSource;

    public RestApiErrorHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> handleException(
            HttpServletRequest request,
            Exception ex,
            Locale locale
    ) {
        Error error = ErrorUtils
                .createError(
                        ErrorCode.GENERIC_ERROR.getErrMsgKey(),
                        ErrorCode.GENERIC_ERROR.getErrorCode(),
                        HttpStatus.INTERNAL_SERVER_ERROR.value()
                )
                .setUrl(request.getRequestURL().toString())
                .setReqMethod(request.getMethod());

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<Error> handleHttpMediaTypeNotSupportedException(
            HttpServletRequest request,
            HttpMediaTypeNotSupportedException ex,
            Locale locale
    ) {
        Error error = ErrorUtils
                .createError(
                        ErrorCode.HTTP_MEDIATYPE_NOT_SUPPORTED.getErrMsgKey(),
                        ErrorCode.HTTP_MEDIATYPE_NOT_SUPPORTED.getErrorCode(),
                        HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
                .setUrl(request.getRequestURL().toString())
                .setReqMethod(request.getMethod());
        return new ResponseEntity<>(
                error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
