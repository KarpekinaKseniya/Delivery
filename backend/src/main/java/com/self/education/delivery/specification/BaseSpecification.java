package com.self.education.delivery.specification;

import org.springframework.data.jpa.domain.Specification;
import com.self.education.delivery.domain.Area;

public interface BaseSpecification {

    Specification<Area> toSpecification(String name, Boolean isDelivary);
}
