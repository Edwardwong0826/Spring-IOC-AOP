package com.spring.configs;

import com.mchange.v2.c3p0.DriverManagerDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 *  spring declarative transaction management
 *
 *  1. import dependency
 *      datasource, database driver, Spring-jdbc template
 *  2. config datasource, JdbcTemplate(Spring provided)
 *  3. mark method with @Transactional, to indicate current method is a transaction method
 *  4. @EnableTransactionManagement open based on annotation transaction management
 *  5. config TransactionManager to control transaction
 *
 *  principle
 *  1. @EnableTransactionManagement
 *      use TransactionManagementConfigurationSelector import component to IOC Container
 *      import two component
 *          AutoProxyRegistrar,
 *          ProxyTransactionManagementConfiguration
 *  2. AutoProxyRegistrar: to register InfrastructureAdvisorAutoProxyCreator component in IOC container
 *          InfrastructureAdvisorAutoProxyCreator:
 *          use BeanPostProcessor mechanism when object created completed, warp object, return one proxy object(advisor), same like AOP use method interceptor those
 *  3. ProxyTransactionManagementConfiguration
 *          1. register transaction advisor in IOC container
 *                 1. transaction advisor annotation information: AnnotationTransactionAttributeSource parse transaction annotation
 *                 2. transactionInterceptor: save transaction property information, TransactionManager
 *                      when in target method execution:
 *                          execute Interceptor chain:
 *                          transactionInterceptor:
 *                                  1. get transaction related property
 *                                  2. get DataSourceTransactionManager/PlatformTransactionManager, get by required one or get from IOC beanFactory by type
 *                                  3. execute target method
 *                                      if exception, get transaction manager to roll back operation
 *                                      if normal, use transaction manager to commit operation
 *
 *
 */
@EnableTransactionManagement
@ComponentScan("com.spring.tx")
@Configuration
public class SpringOfTxConfig {

    @Bean
    public DataSource dataSource() throws Exception
    {
        DriverManagerDataSource dataSource = new DriverManagerDataSource ();
        dataSource.setUser("root");
        dataSource.setPassword("61376554");
        dataSource.setDriverClass("com.mysql.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/springdemo?serverTimezone=UTC");
        return dataSource;
    }


    @Bean
    public JdbcTemplate jdbcTemplate() throws Exception
    {
        // Spring 对@Configuration class special handle, add component to IOC container method, multiple call only will find from IOC, not running
        // again example dataSource()
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource());
        return jdbcTemplate;
    }

    // register DataSourceTransactionManager in the IOC container, choose this one jdbc template, if ORM choose other type
    @Bean
    public DataSourceTransactionManager  transactionManager() throws Exception {
        return new DataSourceTransactionManager(dataSource());
    }
}
