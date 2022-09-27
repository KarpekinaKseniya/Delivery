package com.self.education.delivery.handler;

import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static javax.servlet.http.HttpServletResponse.SC_CONFLICT;
import static javax.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
import static javax.servlet.http.HttpServletResponse.SC_NOT_FOUND;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.only;
import static org.mockito.MockitoAnnotations.openMocks;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.context.request.WebRequest;
import com.self.education.delivery.api.ErrorResponse;
import com.self.education.delivery.exception.EntityNotFoundException;

class RestExceptionHandlerTest {

    private static final String DESCRIPTION = "Some description";
    private static final String ERROR_MESSAGE = "Error message";
    //@formatter:off
    private final ErrorResponse.ErrorResponseBuilder errorResponse = ErrorResponse.builder()
            .description(DESCRIPTION)
            .message(ERROR_MESSAGE);
    //@formatter:on

    @Mock
    private WebRequest webRequest;
    @InjectMocks
    private RestExceptionHandler handler;

    @BeforeEach
    void setUp() {
        openMocks(this);
        given(webRequest.getDescription(false)).willReturn(DESCRIPTION);
    }

    @AfterEach
    void tearDown() {
        then(webRequest).should(only()).getDescription(false);
    }

    @Test
    void shouldHandleGlobalException() {
        final Exception exception = new RuntimeException(ERROR_MESSAGE);

        final ErrorResponse actual = handler.handleGlobalException(exception, webRequest);
        assertThat(actual, is(errorResponse.statusCode(SC_INTERNAL_SERVER_ERROR).build()));
    }

    @Test
    void shouldHandleIllegalArgumentException() {
        final IllegalArgumentException exception = new IllegalArgumentException(ERROR_MESSAGE);

        final ErrorResponse actual = handler.handleIllegalArgumentException(exception, webRequest);
        assertThat(actual, is(errorResponse.statusCode(SC_BAD_REQUEST).build()));
    }

    @Test
    void shouldHandleDataIntegrityViolation() {
        final DataIntegrityViolationException exception =
                new DataIntegrityViolationException("other error message", new RuntimeException(ERROR_MESSAGE));

        final ErrorResponse actual = handler.handleDataIntegrityViolation(exception, webRequest);
        assertThat(actual, is(errorResponse.statusCode(SC_CONFLICT).build()));
    }

    @Test
    void shouldHandleEntityNotFoundException() {
        final EntityNotFoundException exception = new EntityNotFoundException(ERROR_MESSAGE);

        final ErrorResponse actual = handler.handleEntityNotFoundException(exception, webRequest);
        assertThat(actual, is(errorResponse.statusCode(SC_NOT_FOUND).build()));
    }
}