package com.self.education.delivery.mapper;

import org.mapstruct.Mapper;
import com.self.education.delivery.api.ExtraChargeRequest;
import com.self.education.delivery.api.ExtraChargeResponse;
import com.self.education.delivery.domain.ExtraCharge;

@Mapper(componentModel = "spring")
public interface ExtraChargeMapper {

    ExtraChargeResponse mapEntityToResponse(ExtraCharge charge);

    ExtraCharge mapRequestToEntity(ExtraChargeRequest request);
}
