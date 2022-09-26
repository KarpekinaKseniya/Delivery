package com.self.education.delivery.specification;

import static java.util.Objects.isNull;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import com.self.education.delivery.domain.Area;
import com.self.education.delivery.domain.Area_;

@Component
public class AreaSpecification implements BaseSpecification {

    @Override
    public Specification<Area> toSpecification(final String name, final Boolean isDelivery) {
        return Specification.where(startWith(name)).and(delivered(isDelivery));
    }

    private Specification<Area> startWith(final String name) {
        return (root, query, criteria) -> isNull(name) ?
                criteria.isTrue(criteria.literal(true)) :
                criteria.like(criteria.lower(root.get(Area_.NAME)), name.toLowerCase() + "%");
    }

    private Specification<Area> delivered(final Boolean isDelivery) {
        return (root, query, criteria) -> isNull(isDelivery) ?
                criteria.isTrue(criteria.literal(true)) :
                criteria.equal(root.get(Area_.HAS_DELIVERY), isDelivery);
    }
}
