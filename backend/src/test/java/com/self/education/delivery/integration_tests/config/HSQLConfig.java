package com.self.education.delivery.integration_tests.config;

import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.HSQL;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

@Configuration
public class HSQLConfig {

    @Bean
    public DataSource dataSource() {
        //@formatter:off
        return new EmbeddedDatabaseBuilder()
                .setType(HSQL)
                .addScript("integration/db/db_setup.sql")
                .generateUniqueName(true).build();
        //@formatter:on
    }
}
