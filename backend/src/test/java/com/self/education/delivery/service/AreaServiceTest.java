package com.self.education.delivery.service;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.lang.String.format;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.MockitoAnnotations.openMocks;
import static com.self.education.delivery.helper.AreaHelper.AREA_NAME;
import static com.self.education.delivery.helper.AreaHelper.ID;
import static com.self.education.delivery.helper.AreaHelper.areaEntityBuilder;
import static com.self.education.delivery.helper.AreaHelper.areaRequestBuilder;
import static com.self.education.delivery.helper.AreaHelper.areaResponseBuilder;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import com.self.education.delivery.api.AreaRequest;
import com.self.education.delivery.api.AreaResponse;
import com.self.education.delivery.domain.Area;
import com.self.education.delivery.exception.EntityNotFoundException;
import com.self.education.delivery.mapper.AreaMapper;
import com.self.education.delivery.repository.AreaRepository;
import com.self.education.delivery.specification.BaseSpecification;

class AreaServiceTest {

    private static final Sort SORT = Sort.by(Sort.Direction.ASC, "name");
    private static final Exception SOME_EXCEPTION = new RuntimeException("Some Error Message(");

    private final Specification<Area> mockSpecification = (Specification<Area>) mock(Specification.class);

    @Mock
    private AreaRepository areaRepository;
    @Mock
    private AreaMapper areaMapper;
    @Mock
    private BaseSpecification specification;

    private AreaService service;

    @BeforeEach
    void setUp() {
        openMocks(this);
        service = new AreaServiceImpl(areaRepository, areaMapper, specification);
    }

    @AfterEach
    void tearDown() {
        verifyNoMoreInteractions(areaMapper, areaRepository, specification);
    }

    @Test
    void shouldFindAllSuccess() {
        final List<AreaResponse> expected = List.of(areaResponseBuilder().build());

        given(specification.toSpecification(AREA_NAME, FALSE)).willReturn(mockSpecification);
        given(areaRepository.findAll(mockSpecification, SORT)).willReturn(List.of(areaEntityBuilder().build()));
        given(areaMapper.mapEntityToResponse(areaEntityBuilder().build())).willReturn(areaResponseBuilder().build());

        final List<AreaResponse> actual = service.findAll(AREA_NAME, FALSE);
        assertThat(actual, is(expected));

        then(specification).should(only()).toSpecification(AREA_NAME, FALSE);
        then(areaRepository).should(only()).findAll(mockSpecification, SORT);
        then(areaMapper).should(only()).mapEntityToResponse(areaEntityBuilder().build());
    }

    @Test
    void shouldFindAllFailureWhenAreaSpecificationThrowException() {
        given(specification.toSpecification(AREA_NAME, FALSE)).willThrow(SOME_EXCEPTION);

        final Exception actual = assertThrows(RuntimeException.class, () -> service.findAll(AREA_NAME, FALSE));
        assertThat(actual, is(SOME_EXCEPTION));

        then(specification).should(only()).toSpecification(AREA_NAME, FALSE);
    }

    @Test
    void shouldFindAllFailureWhenAreaRepositoryThrowException() {
        given(specification.toSpecification(AREA_NAME, FALSE)).willReturn(mockSpecification);
        given(areaRepository.findAll(mockSpecification, SORT)).willThrow(SOME_EXCEPTION);

        final Exception actual = assertThrows(RuntimeException.class, () -> service.findAll(AREA_NAME, FALSE));
        assertThat(actual, is(SOME_EXCEPTION));

        then(specification).should(only()).toSpecification(AREA_NAME, FALSE);
        then(areaRepository).should(only()).findAll(mockSpecification, SORT);
    }

    @Test
    void shouldFindAllFailureWhenAreaMapperThrowException() {
        given(specification.toSpecification(AREA_NAME, FALSE)).willReturn(mockSpecification);
        given(areaRepository.findAll(mockSpecification, SORT)).willReturn(List.of(areaEntityBuilder().build()));
        given(areaMapper.mapEntityToResponse(areaEntityBuilder().build())).willThrow(SOME_EXCEPTION);

        final Exception actual = assertThrows(RuntimeException.class, () -> service.findAll(AREA_NAME, FALSE));
        assertThat(actual, is(SOME_EXCEPTION));

        then(specification).should(only()).toSpecification(AREA_NAME, FALSE);
        then(areaRepository).should(only()).findAll(mockSpecification, SORT);
        then(areaMapper).should(only()).mapEntityToResponse(areaEntityBuilder().build());
    }

    @Test
    void shouldUpdateAreaSuccess() {
        final AreaRequest request = areaRequestBuilder().build();
        final Area entity = areaEntityBuilder().build();

        given(areaRepository.existsById(ID)).willReturn(TRUE);
        given(areaMapper.mapRequestToEntity(ID, request)).willReturn(entity);
        given(areaRepository.save(entity)).willReturn(areaEntityBuilder().build());

        service.updateArea(ID, request);

        then(areaRepository).should(times(1)).existsById(ID);
        then(areaMapper).should(only()).mapRequestToEntity(ID, request);
        then(areaRepository).should(times(1)).save(entity);
    }

    @Test
    void shouldUpdateAreaFailureWhenAreaIsNotExists() {
        given(areaRepository.existsById(ID)).willReturn(FALSE);

        final RuntimeException actual =
                assertThrows(EntityNotFoundException.class, () -> service.updateArea(ID, areaRequestBuilder().build()));
        assertThat(actual.getMessage(), is(format("Area not found by id = %s", ID)));

        then(areaRepository).should(times(1)).existsById(ID);
    }
}