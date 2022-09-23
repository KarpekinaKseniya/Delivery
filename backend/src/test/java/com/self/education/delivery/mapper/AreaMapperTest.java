package com.self.education.delivery.mapper;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static com.self.education.delivery.helper.AreaHelper.ID;
import static com.self.education.delivery.helper.AreaHelper.areaEntityBuilder;
import static com.self.education.delivery.helper.AreaHelper.areaRequestBuilder;
import static com.self.education.delivery.helper.AreaHelper.areaResponseBuilder;
import static com.self.education.delivery.helper.ExtraChargeHelper.averageExtraChargeEntity;
import static com.self.education.delivery.helper.ExtraChargeHelper.averageExtraChargeRequest;
import static com.self.education.delivery.helper.ExtraChargeHelper.averageExtraChargeResponse;
import static com.self.education.delivery.helper.ExtraChargeHelper.minExtraChargeEntity;
import static com.self.education.delivery.helper.ExtraChargeHelper.minExtraChargeRequest;
import static com.self.education.delivery.helper.ExtraChargeHelper.minExtraChargeResponse;

import java.util.stream.Stream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.self.education.delivery.api.AreaRequest;
import com.self.education.delivery.api.AreaResponse;
import com.self.education.delivery.domain.Area;

@ExtendWith(MockitoExtension.class)
class AreaMapperTest {

    @Mock
    private ExtraChargeMapper chargeMapper;
    @InjectMocks
    private final AreaMapper mapper = new AreaMapperImpl();

    @AfterEach
    void verify() {
        verifyNoMoreInteractions(chargeMapper);
    }

    private static Stream<Arguments> entityAndResponseData() {
        //@formatter:off
        return Stream.of(
                Arguments.of(null, null),
                Arguments.of(areaEntityBuilder().extraCharges(null).build(), areaResponseBuilder().extraCharges(null).build())
        );
        //@formatter:on
    }

    private static Stream<Arguments> requestAndEntityData() {
        //@formatter:off
        return Stream.of(
                Arguments.of(null,null, null),
                Arguments.of(null, areaRequestBuilder().extraCharges(null).build(), areaEntityBuilder().id(null).extraCharges(null).build()),
                Arguments.of(ID, null, Area.builder().id(ID).build())
        );
        //@formatter:on
    }

    @ParameterizedTest
    @MethodSource("requestAndEntityData")
    void shouldMapRequestToEntityWhenNull(final Long id, final AreaRequest request, final Area expected) {
        assertThat(mapper.mapRequestToEntity(id, request), is(expected));
    }

    @ParameterizedTest
    @MethodSource("entityAndResponseData")
    void shouldMapEntityToResponseWhenNull(final Area area, final AreaResponse response) {
        assertThat(mapper.mapEntityToResponse(area), is(response));
    }

    @Test
    void shouldMapRequestToEntity() {
        given(chargeMapper.mapRequestToEntity(averageExtraChargeRequest())).willReturn(averageExtraChargeEntity());
        given(chargeMapper.mapRequestToEntity(minExtraChargeRequest())).willReturn(minExtraChargeEntity());

        assertThat(mapper.mapRequestToEntity(ID, areaRequestBuilder().build()), is(areaEntityBuilder().build()));

        then(chargeMapper).should(times(1)).mapRequestToEntity(averageExtraChargeRequest());
        then(chargeMapper).should(times(1)).mapRequestToEntity(minExtraChargeRequest());
    }

    @Test
    void shouldMapEntityToResponse() {
        given(chargeMapper.mapEntityToResponse(averageExtraChargeEntity())).willReturn(averageExtraChargeResponse());
        given(chargeMapper.mapEntityToResponse(minExtraChargeEntity())).willReturn(minExtraChargeResponse());

        assertThat(mapper.mapEntityToResponse(areaEntityBuilder().build()), is(areaResponseBuilder().build()));

        then(chargeMapper).should(times(1)).mapEntityToResponse(averageExtraChargeEntity());
        then(chargeMapper).should(times(1)).mapEntityToResponse(minExtraChargeEntity());
    }
}