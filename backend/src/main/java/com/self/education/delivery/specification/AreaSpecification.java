package com.self.education.delivery.specification;

import static java.util.Objects.isNull;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import com.self.education.delivery.domain.Area;
import com.self.education.delivery.domain.Area_;

@Component
public class AreaSpecification implements BaseSpecification {

    @Override
    public Specification<Area> toSpecification(final String name) {
        return Specification.where(startWith(name));
    }

    private Specification<Area> startWith(final String name) {
        return (root, query, criteria) -> isNull(name) ?
                criteria.isTrue(criteria.literal(true)) :
                criteria.like(criteria.lower(root.get(Area_.NAME)), name.toLowerCase() + "%");
    }
}
