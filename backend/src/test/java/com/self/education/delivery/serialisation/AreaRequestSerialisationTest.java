package com.self.education.delivery.serialisation;

import static com.self.education.delivery.helper.AreaHelper.areaRequestBuilder;

import org.junit.jupiter.api.BeforeEach;
import com.self.education.delivery.api.AreaRequest;

class AreaRequestSerialisationTest extends JsonTestBase<AreaRequest> {

    @BeforeEach
    void before() {
        expected = () -> areaRequestBuilder().build();
        fileName = "expected_area_request.json";
        expectedType = AreaRequest.class;
    }
}
