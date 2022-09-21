package com.self.education.delivery.resource;

import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import com.self.education.delivery.api.AreaRequest;
import com.self.education.delivery.api.AreaResponse;
import com.self.education.delivery.service.AreaService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AreaResource {

    private final AreaService areaService;

    public ResponseEntity<Page<AreaResponse>> findAllAreas(final String name) {
        throw new NotYetImplementedException("Should implemented later");
    }

    public ResponseEntity<Long> updateArea(final AreaRequest request) {
        throw new NotYetImplementedException("Should implemented later");
    }
}
