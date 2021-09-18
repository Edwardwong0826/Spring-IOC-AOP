package com.spring.configs;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.spring.beans.Yellow;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.AbstractApplicationContext;

import javax.sql.DataSource;


/**
 *     Profile:
 *         Spring provided can according environment to dynamic switch or activate bean or component function
 *     switch prod, test environment
 *     data source: A/B/C
 *
 *     1. once add @Profile bean, only the environment activate will be registered,
 *     2. when add in class, class  will be loaded based on environment and inside @profile can activate
 *     3. those bean without @Profile in all time is loaded and register
 */
@Profile("test")
@PropertySource("classpath:/dbconfig.properties")
@Configuration
public class SpringConfigOfProfile {

    @Value("${db.user}")
    private String User;


    @Profile("dev")
    @Bean("DevDataSource")
    public DataSource dataSourceDev(@Value("${db.password}") String password) throws Exception {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(User);
        dataSource.setPassword(password);
        dataSource.setJdbcUrl("jdbc://localhost:3306/tet");
        dataSource.setDriverClass("com.mysql.jdbc.Driver");
        return null;
    }

    @Profile("test")
    @Bean
    public Yellow yellow()
    {
        return new Yellow();
    }

    @Profile("test")
    @Bean("testDataSource")
    public DataSource dataSourceTest(@Value("${db.password}") String password) throws Exception {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(User);
        dataSource.setPassword(password);
        dataSource.setJdbcUrl("jdbc://localhost:3306/tet");
        dataSource.setDriverClass("com.mysql.jdbc.Driver");
        return null;
    }

    @Profile("prod")
    @Bean("prodDataSource")
    public DataSource dataSourceProd(@Value("${db.password}") String password) throws Exception {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(User);
        dataSource.setPassword(password);
        dataSource.setJdbcUrl("jdbc://localhost:3306/tet");
        dataSource.setDriverClass("com.mysql.jdbc.Driver");
        return null;
    }

}
