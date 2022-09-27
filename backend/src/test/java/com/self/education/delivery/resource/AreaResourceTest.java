package com.self.education.delivery.resource;

import static java.lang.Boolean.FALSE;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.ResponseEntity.ok;
import static com.self.education.delivery.helper.AreaHelper.ID;
import static com.self.education.delivery.helper.AreaHelper.areaRequestBuilder;
import static com.self.education.delivery.helper.AreaHelper.areaResponseBuilder;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import com.self.education.delivery.api.AreaResponse;
import com.self.education.delivery.service.AreaService;

class AreaResourceTest {
    private static final String AREA_NAME = "Bul";
    private static final ResponseEntity<Void> UPDATE_SUCCESS_RESULT = new ResponseEntity<>(NO_CONTENT);
    private static final Exception EXCEPTION = new RuntimeException("Error Message");

    @Mock
    private AreaService areaService;

    private AreaResource resource;

    @BeforeEach
    void setUp() {
        openMocks(this);
        resource = new AreaResource(areaService);
    }

    @AfterEach
    void tearDown() {
        verifyNoMoreInteractions(areaService);
    }

    @Test
    void shouldFindAllAreas() {
        final List<AreaResponse> expected = List.of(areaResponseBuilder().build());
        given(areaService.findAll(AREA_NAME, FALSE)).willReturn(expected);

        final ResponseEntity<List<AreaResponse>> actual = resource.findAllAreas(AREA_NAME, FALSE);
        assertThat(actual, is(ok(expected)));

        then(areaService).should(only()).findAll(AREA_NAME, FALSE);
    }

    @Test
    void shouldUpdateArea() {
        willDoNothing().given(areaService).updateArea(ID, areaRequestBuilder().build());

        final ResponseEntity<Void> actual = resource.updateArea(ID, areaRequestBuilder().build());
        assertThat(actual, is(UPDATE_SUCCESS_RESULT));

        then(areaService).should(only()).updateArea(ID, areaRequestBuilder().build());
    }

    @Test
    void shouldFindAllAreasThrowsException() {
        given(areaService.findAll(AREA_NAME, FALSE)).willThrow(EXCEPTION);

        final Exception actual = assertThrows(RuntimeException.class, () -> resource.findAllAreas(AREA_NAME, FALSE));
        assertThat(actual, is(EXCEPTION));

        then(areaService).should(only()).findAll(AREA_NAME, FALSE);
    }

    @Test
    void shouldUpdateAreaThrowsException() {
        willThrow(EXCEPTION).given(areaService).updateArea(ID, areaRequestBuilder().build());

        final Exception actual =
                assertThrows(RuntimeException.class, () -> resource.updateArea(ID, areaRequestBuilder().build()));
        assertThat(actual, is(EXCEPTION));

        then(areaService).should(only()).updateArea(ID, areaRequestBuilder().build());
    }
}