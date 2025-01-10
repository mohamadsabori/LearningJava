package com.nl.JdbcDriverConfig.Jdbc;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SimpleDataSourceCfgTest {
    @Test
    public void data_source_should_created_successfully() {
        var ctx = new AnnotationConfigApplicationContext(SimpleDataSourceCfg.class);
        var dataSource = ctx.getBean("dataSource", DataSource.class);
        assertNotNull(dataSource);
        try (var st = ctx.getBean(DataSource.class).getConnection().prepareStatement("select 1;");
             var sr = st.executeQuery();
        ) {
            while (sr.next()) {
                assertEquals(sr.getInt(1), 1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
