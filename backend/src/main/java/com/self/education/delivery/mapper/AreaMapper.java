package com.self.education.delivery.mapper;

import com.self.education.delivery.api.AreaRequest;
import com.self.education.delivery.api.AreaResponse;
import com.self.education.delivery.domain.Area;

public interface AreaMapper {

    AreaResponse mapEntityToResponse(Area area);

    Area mapRequestToEntity(Long id, AreaRequest areaRequest);
}
