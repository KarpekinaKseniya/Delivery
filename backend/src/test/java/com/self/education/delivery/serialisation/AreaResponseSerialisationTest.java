package com.self.education.delivery.serialisation;

import static com.self.education.delivery.helper.AreaHelper.areaResponseBuilder;

import org.junit.jupiter.api.BeforeEach;
import com.self.education.delivery.api.AreaResponse;

class AreaResponseSerialisationTest extends JsonTestBase<AreaResponse> {

    @BeforeEach
    void beforeEach() {
        expected = () -> areaResponseBuilder().build();
        expectedType = AreaResponse.class;
        fileName = "expected_area_response.json";
    }
}
