package com.self.education.delivery.service;

import static java.lang.String.format;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.self.education.delivery.api.AreaRequest;
import com.self.education.delivery.api.AreaResponse;
import com.self.education.delivery.domain.Area;
import com.self.education.delivery.exception.EntityNotFoundException;
import com.self.education.delivery.mapper.AreaMapper;
import com.self.education.delivery.repository.AreaRepository;
import com.self.education.delivery.specification.BaseSpecification;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AreaServiceImpl implements AreaService {

    private static final String SORT_PROPERTY = "name";

    private final AreaRepository areaRepository;
    private final AreaMapper areaMapper;
    private final BaseSpecification specification;

    @Override
    public List<AreaResponse> findAll(final String name) {
        final Sort sort = Sort.by(Sort.Direction.ASC, SORT_PROPERTY);
        final Specification<Area> predicate = specification.toSpecification(name);
        return areaRepository.findAll(predicate, sort).stream().map(areaMapper::mapEntityToResponse).toList();
    }

    @Override
    public void updateArea(final Long id, final AreaRequest request) {
        if (!areaRepository.existsById(id)) {
            throw new EntityNotFoundException(format("Area not found by id = %s", id));
        }
        areaRepository.save(areaMapper.mapRequestToEntity(id, request));
    }
}
