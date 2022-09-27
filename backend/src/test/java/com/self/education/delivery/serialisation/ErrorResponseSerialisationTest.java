package com.self.education.delivery.serialisation;

import static javax.servlet.http.HttpServletResponse.SC_NOT_FOUND;

import org.junit.jupiter.api.BeforeEach;
import com.self.education.delivery.api.ErrorResponse;

class ErrorResponseSerialisationTest extends JsonTestBase<ErrorResponse> {

    @BeforeEach
    void beforeEach() {
        //@formatter:off
        expected = () -> ErrorResponse.builder()
                .statusCode(SC_NOT_FOUND)
                .message("some error message")
                .description("Some error description").build();
        //@formatter:on
        fileName = "expected_error_response.json";
        expectedType = ErrorResponse.class;
    }
}
