package com.self.education.delivery.handler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import com.self.education.delivery.api.ErrorResponse;
import com.self.education.delivery.exception.EntityNotFoundException;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(value = INTERNAL_SERVER_ERROR)
    public ErrorResponse handleGlobalException(final Exception ex, final WebRequest request) {
        //@formatter:off
        return new ErrorResponse(
                INTERNAL_SERVER_ERROR.value(),
                ex.getMessage(),
                request.getDescription(false)
        );
        //@formatter:on
    }

    @ExceptionHandler
    @ResponseStatus(value = BAD_REQUEST)
    public ErrorResponse handleIllegalArgumentException(final IllegalArgumentException ex, final WebRequest request) {
        //@formatter:off
        return new ErrorResponse(
                BAD_REQUEST.value(),
                ex.getMessage(),
                request.getDescription(false)
        );
        //@formatter:on
    }

    @ExceptionHandler({ DataIntegrityViolationException.class })
    @ResponseStatus(value = CONFLICT)
    public ErrorResponse handleDataIntegrityViolation(final DataIntegrityViolationException ex,
            final WebRequest request) {
        //@formatter:off
        return new ErrorResponse(
                CONFLICT.value(),
                ex.getCause().getMessage(),
                request.getDescription(false)
        );
        //@formatter:on
    }

    @ExceptionHandler({ EntityNotFoundException.class })
    @ResponseStatus(value = NOT_FOUND)
    public ErrorResponse handleEntityNotFoundException(final EntityNotFoundException ex, final WebRequest request) {
        //@formatter:off
        return new ErrorResponse(
                NOT_FOUND.value(),
                ex.getMessage(),
                request.getDescription(false)
        );
        //@formatter:on
    }
}
