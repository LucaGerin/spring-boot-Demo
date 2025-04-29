package com.pluralsight.conference_demo.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

//Instead of relying on the default Spring Boot auto-configuration (like using application.properties or application.yml), you can manually define and customize your database connection.
//In more complex applications where you need to connect to multiple databases, defining beans manually like this is mandatory (Spring Boot can only auto-configure one by default).
//Moreover, you might want to run some custom logic (like logging, validation) when the database connection is established, which you can easily do here (e.g., the System.out.println() line).

@Configuration //tells Spring that this class contains beans (objects managed by the Spring container) that should be created at startup
public class PersistenceConfiguration {

    @Bean
    public DataSource dataSource(){ //configure a PostgreSQL database connection manually
        DataSourceBuilder builder = DataSourceBuilder.create();
        builder.url("jdbc:postgresql://localhost:5432/conference_app");
        System.out.println("My custom datasource bean has been initialized and set");
        return builder.build();
    }

}
