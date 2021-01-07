package com.spring.configs;

import com.spring.beans.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

//to read config file inside value to save into running environment variable, once load use ${} to retrieve the value
@PropertySource(value={"classpath:/person.properties"})
@Configuration
public class SpringConfigOfPropertyValues {

    @Bean
    public Person person()
    {
        return new Person();
    }
}
