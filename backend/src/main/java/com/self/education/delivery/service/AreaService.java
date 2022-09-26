package com.self.education.delivery.service;

import java.util.List;
import com.self.education.delivery.api.AreaRequest;
import com.self.education.delivery.api.AreaResponse;

public interface AreaService {

    List<AreaResponse> findAll(String name, Boolean isDelivery);

    Long updateArea(Long id, AreaRequest request);
}
