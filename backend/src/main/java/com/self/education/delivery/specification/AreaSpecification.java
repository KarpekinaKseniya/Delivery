package com.self.education.delivery.specification;

import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.data.jpa.domain.Specification;
import com.self.education.delivery.domain.Area;

public class AreaSpecification implements BaseSpecification {

    @Override
    public Specification<Area> toSpecification(final String name) {
        throw new NotYetImplementedException("Should implemented later");
    }
}
