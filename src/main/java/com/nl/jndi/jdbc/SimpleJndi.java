package com.nl.jndi.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jndi.JndiTemplate;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class SimpleJndi {
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleJndi.class);

    @Bean
    DataSource dataSource() {
        JndiTemplate jndiTemplate = new JndiTemplate();
        try {
            return (DataSource) jndiTemplate.lookup("java:comp/env/jdbc/mudb");
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        var ctx = new AnnotationConfigApplicationContext(SimpleJndi.class);
        try (var result = ctx.getBean("dataSource", DataSource.class).
                getConnection().
                prepareStatement("select 1;").
                executeQuery()) {
            while (result.next()) {
                LOGGER.info("Query result {}", result.getInt(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
