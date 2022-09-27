package com.self.education.delivery.repository;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasProperty;

import java.util.List;
import java.util.stream.Stream;
import org.hamcrest.Matcher;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.TestDatabaseAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import com.self.education.delivery.domain.Area;
import com.self.education.delivery.integration_tests.config.HSQLConfig;
import com.self.education.delivery.specification.AreaSpecification;
import com.self.education.delivery.specification.BaseSpecification;

@DataJpaTest(excludeAutoConfiguration = TestDatabaseAutoConfiguration.class)
@Import(HSQLConfig.class)
@TestPropertySource(locations = "classpath:/application-test.properties")
class AreaRepositoryAndSpecificationTest {

    private final BaseSpecification areaSpecification = new AreaSpecification();
    @Autowired
    private AreaRepository repository;

    private static Stream<Arguments> data() {
        //@formatter:off
        return Stream.of(
                Arguments.of(null, null, 5, containsInAnyOrder(
                        hasProperty("name", is("Bulawayo")),
                        hasProperty("name", is("Phnom Penh")),
                        hasProperty("name", is("Palembang")),
                        hasProperty("name", is("Amsterdam")),
                        hasProperty("name", is("Gaborone"))
                        )),
                Arguments.of("p", null, 2, containsInAnyOrder(
                        hasProperty("name", is("Phnom Penh")),
                        hasProperty("name", is("Palembang")))),
                Arguments.of(null, false, 2, containsInAnyOrder(
                        hasProperty("name", is("Bulawayo")),
                        hasProperty("name", is("Palembang"))
                )),
                Arguments.of("bul", false, 1, containsInAnyOrder(hasProperty("name", is("Bulawayo"))))
        );
        //@formatter:on
    }

    @ParameterizedTest
    @MethodSource("data")
    @Sql({ "classpath:integration/db/db_cleanup.sql", "/integration/db/db_data.sql" })
    void shouldFindAllWithParams(final String name, final Boolean isDelivery, final int expectedSize,
            final Matcher<Iterable<? extends Area>> expected) {
        final Sort sort = Sort.by(Sort.Direction.ASC, "name");
        final Specification<Area> spec = areaSpecification.toSpecification(name, isDelivery);

        final List<Area> actual = repository.findAll(spec, sort);

        assertThat(actual.size(), is(expectedSize));
        assertThat(actual, expected);
    }
}