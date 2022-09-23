package com.self.education.delivery.mapper;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static com.self.education.delivery.helper.ExtraChargeHelper.averageExtraChargeEntity;
import static com.self.education.delivery.helper.ExtraChargeHelper.averageExtraChargeRequest;
import static com.self.education.delivery.helper.ExtraChargeHelper.averageExtraChargeResponse;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import com.self.education.delivery.api.ExtraChargeRequest;
import com.self.education.delivery.api.ExtraChargeResponse;
import com.self.education.delivery.domain.ExtraCharge;

class ExtraChargeMapperTest {

    private final ExtraChargeMapper mapper = new ExtraChargeMapperImpl();

    private static Stream<Arguments> entityAndResponseData() {
        //@formatter:off
        return Stream.of(
                Arguments.of(null, null),
                Arguments.of(averageExtraChargeEntity(), averageExtraChargeResponse())
        );
        //@formatter:on
    }

    private static Stream<Arguments> requestAndEntityData() {
        return Stream.of(Arguments.of(null, null),
                Arguments.of(averageExtraChargeRequest(), averageExtraChargeEntity()));
    }

    @ParameterizedTest
    @MethodSource("entityAndResponseData")
    void shouldMapEntityToResponse(final ExtraCharge charge, final ExtraChargeResponse response) {
        assertThat(mapper.mapEntityToResponse(charge), is(response));
    }

    @ParameterizedTest
    @MethodSource("requestAndEntityData")
    void shouldMapRequestToEntity(final ExtraChargeRequest request, final ExtraCharge charge) {
        assertThat(mapper.mapRequestToEntity(request), is(charge));
    }
}