package com.nl.JdbcDriverConfig.Jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.nio.file.Paths;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.stream.Collectors;

@Configuration
@PropertySource("classpath:db/jdbc.properties")
public class SimpleDataSourceCfg {
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleDataSourceCfg.class);

    @Value("${jdbc.url}")
    String Url;
    @Value("${jdbc.passWord}")
    private String passWord;
    @Value("${jdbc.userName}")
    private String userName;
    @Value("${jdbc.className}")
    private String className;

    @Bean
    public DataSource dataSource() {
        try {
            var dataSource = new SimpleDriverDataSource();
            var path = Paths.get(".");
            LOGGER.debug("Absolute path is {}", path.normalize().toAbsolutePath());
            LOGGER.debug("Spring framework class {}", Class.forName(ApplicationContext.class.getName()));
            LOGGER.debug("java.class.path {}", Arrays.stream(System.getProperty("java.class.path").split(":"))
                    .filter(e -> e.equals("/home/mohammad/.m2/repository/org/postgresql/postgresql/42.6.0/postgresql-42.6.0.jar"))
                    .collect(Collectors.joining()));
            Class<? extends Driver> driver = (Class<? extends Driver>) Class.forName(className);
            dataSource.setDriverClass(driver);
            dataSource.setUrl(Url);
            dataSource.setUsername(userName);
            dataSource.setPassword(passWord);
            dataSource.setSchema("mudb");
            return dataSource;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        var ctx = new AnnotationConfigApplicationContext(SimpleDataSourceCfg.class);
        try (var st = ctx.getBean(DataSource.class).getConnection().prepareStatement("select 1;");
             var sr = st.executeQuery();
        ) {
            while (sr.next()) {
                LOGGER.debug("FIRST db value {}", sr.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
