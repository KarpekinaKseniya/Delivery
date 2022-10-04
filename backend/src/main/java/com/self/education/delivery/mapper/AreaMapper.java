package com.self.education.delivery.mapper;

import org.mapstruct.Mapper;
import com.self.education.delivery.api.AreaRequest;
import com.self.education.delivery.api.AreaResponse;
import com.self.education.delivery.config.NonBuilderMapperConfig;
import com.self.education.delivery.domain.Area;

@Mapper(componentModel = "spring", uses = ExtraChargeMapper.class, config = NonBuilderMapperConfig.class)
public interface AreaMapper {

    AreaResponse mapEntityToResponse(Area area);

    Area mapRequestToEntity(Long id, AreaRequest areaRequest);
}
