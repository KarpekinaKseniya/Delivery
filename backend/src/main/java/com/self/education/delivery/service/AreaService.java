package com.self.education.delivery.service;

import org.springframework.data.domain.Page;
import com.self.education.delivery.api.AreaRequest;
import com.self.education.delivery.resource.AreaResource;

public interface AreaService {

    Page<AreaResource> findAll(String name);

    Long updateArea(AreaRequest request);
}
