package com.spring.configs;

import com.spring.beans.CustomerOld;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration // if this dont have @configuration, at Owener class cannot autowired the getCustomerBean1 bean
public class SpringConfig2 {
	
    @Bean
    public CustomerOld getCustomerBean()
    {
    	return new CustomerOld();
    }
    
    @Bean
    public CustomerOld getCustomerBean1()
    {
    	CustomerOld c = new CustomerOld();
    	// surprisingly, in @Bean method called object setter will also called the auto wiring go to get bean value
    	// instead of the below setter value
    	c.setID(1);
    	c.setName("WY");

    	return c;

    }
    
    
    @Bean
    public int ID()
    {
    	return 10;
    }
    
    
    @Bean
    public double ID1()
    {
    	return 20;
    }
    
	
    @Bean
    public String name1()
    {
    	return "Boss";
    }
    
}
