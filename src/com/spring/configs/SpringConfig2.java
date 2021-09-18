package com.spring.configs;

import com.spring.beans.*;
import com.spring.conditional.LinuxCondition;
import com.spring.conditional.MyBeanDefinitionRegistrar;
import com.spring.conditional.MyImportSelector;
import com.spring.conditional.WindowsCondition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;



@Configuration // if this don't have @configuration, at Owner class cannot autowired the getCustomerBean1 bean
// import the class to IOC, default id is full class name
@Import({Color.class, Red.class, Car.class, MyImportSelector.class, MyBeanDefinitionRegistrar.class})
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
