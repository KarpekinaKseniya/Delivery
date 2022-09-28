package com.self.education.delivery.integration_tests;

import static java.util.Collections.emptyMap;
import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_INTERNAL_SERVER_ERROR;
import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static org.apache.http.HttpStatus.SC_NO_CONTENT;
import static org.apache.http.HttpStatus.SC_OK;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static uk.co.datumedge.hamcrest.json.SameJSONAs.sameJSONAs;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import com.self.education.delivery.DeliveryApplication;
import com.self.education.delivery.integration_tests.config.HSQLConfig;

@SpringBootTest(classes = { DeliveryApplication.class }, webEnvironment = RANDOM_PORT)
@TestPropertySource(locations = { "classpath:/application-test.properties" })
@Import(HSQLConfig.class)
@EnableConfigurationProperties
class AreaResourceIT {

    @Value("${local.server.port}")
    private int port;

    private static Stream<Arguments> provideParamsToFindAllArea() {
        //@formatter:off
        return Stream.of(
                Arguments.of(emptyMap(), "all_areas_response_200.json"),
                Arguments.of(Map.of("name", "p"), "start_p_areas_response_200.json"),
                Arguments.of(Map.of("isDelivery", "false"), "area_which_is_not_delivery_response_200.json"),
                Arguments.of(Map.of("name", "p", "isDelivery", "false"), "find_area_with_all_param_response_200.json")
        );
        //@formatter:on
    }

    @Test
    @Sql(executionPhase = BEFORE_TEST_METHOD, scripts = "classpath:integration/db/db_drop_table.sql")
    @Sql(executionPhase = AFTER_TEST_METHOD, scripts = { "classpath:integration/db/db_setup.sql" })
    void shouldReturnInternalErrorWhenDbDoesNotExist() throws IOException {
        expectFindAllArea(emptyMap(), SC_INTERNAL_SERVER_ERROR, "db_not_exist_error.json");
    }

    @ParameterizedTest
    @MethodSource("provideParamsToFindAllArea")
    @Sql(executionPhase = BEFORE_TEST_METHOD, scripts = { "classpath:integration/db/db_cleanup.sql",
            "classpath:integration/db/db_data.sql" })
    void shouldFindAllAreaReturnSuccess(final Map<String, String> params, final String responseFile)
            throws IOException {
        expectFindAllArea(params, SC_OK, responseFile);
    }

    @Test
    @Sql(executionPhase = BEFORE_TEST_METHOD, scripts = { "classpath:integration/db/db_cleanup.sql",
            "classpath:integration/db/db_data.sql" })
    void shouldUpdateAreaReturnNoContent() throws IOException {
        expectUpdateAreaSuccess();
    }

    @Test
    @Sql(executionPhase = BEFORE_TEST_METHOD, scripts = { "classpath:integration/db/db_cleanup.sql",
            "classpath:integration/db/db_data.sql" })
    void shouldUpdateAreaReturnBadRequestWhenWrongRequest() throws IOException {
        expectUpdateAreaFailure(1L, "update_area_request_400.json", "update_area_response_400.json", SC_BAD_REQUEST);
    }

    @Test
    @Sql(executionPhase = BEFORE_TEST_METHOD, scripts = { "classpath:integration/db/db_cleanup.sql",
            "classpath:integration/db/db_data.sql" })
    void shouldUpdateAreaReturnBadRequestWhenWrongId() throws IOException {
        expectUpdateAreaFailure(0L, "update_area_success.json", "update_area_wrong_id_response_400.json",
                SC_BAD_REQUEST);
    }

    @Test
    @Sql(executionPhase = BEFORE_TEST_METHOD, scripts = { "classpath:integration/db/db_cleanup.sql",
            "classpath:integration/db/db_data.sql" })
    void shouldUpdateAreaReturnNotFound() throws IOException {
        expectUpdateAreaFailure(157L, "update_area_success.json", "update_area_response_404.json", SC_NOT_FOUND);
    }

    private void expectFindAllArea(final Map<String, String> params, final int statusCode, final String responseFile)
            throws IOException {
        //@formatter:off
        given()
                .contentType(JSON)
                .accept(JSON)
                .params(params)
                .get(buildRequestUrlStr())
                .then()
                .assertThat()
                .statusCode(statusCode)
                .body(sameJSONAs(getResponse(responseFile)));
        //@formatter:on
    }

    private void expectUpdateAreaSuccess() throws IOException {
        //@formatter:off
        given()
                .contentType(JSON)
                .accept(JSON)
                .body(getRequest("update_area_success.json"))
                .pathParam("id", 3L)
                .patch(buildRequestUrlStr() + "/{id}")
                .then()
                .assertThat()
                .statusCode(SC_NO_CONTENT);
        //@formatter:on
    }

    private void expectUpdateAreaFailure(final Long id, final String requestFile, final String responseFile,
            final int statusCode) throws IOException {
        //@formatter:off
        given()
                .contentType(JSON)
                .accept(JSON)
                .body(getRequest(requestFile))
                .pathParam("id", id)
                .patch(buildRequestUrlStr() + "/{id}")
                .then()
                .assertThat()
                .statusCode(statusCode)
                .body(sameJSONAs(getResponse(responseFile)));
        //@formatter:on
    }

    private String buildRequestUrlStr() {
        return "http://localhost:" + port + "/v1/areas";
    }

    private String getResponse(String file) throws IOException {
        return new String(Files.readAllBytes(Paths.get("src/test/resources/integration/response/" + file)));
    }

    private String getRequest(String file) throws IOException {
        return new String(Files.readAllBytes(Paths.get("src/test/resources/integration/request/" + file)));
    }
}
