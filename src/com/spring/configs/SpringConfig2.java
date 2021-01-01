package com.spring.configs;

import com.spring.beans.CustomerOld;
import com.spring.beans.Person;
import com.spring.conditional.LinuxCondition;
import com.spring.conditional.WindowsCondition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;


//@Conditional({LinuxCondition.class}) // if put on class, then the entire class bean only will register if the conditional is true
@Configuration // if this don't have @configuration, at Owner class cannot autowired the getCustomerBean1 bean
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

    /**
     * @Conditional can based on condition to determine, if true then register the bean into IOC container
     * example if system is windows, then add Bill to container
     * if system is linus, then add Linus to container
     */
    @Conditional({WindowsCondition.class})
    @Bean("Bill")
    public Person person(){
        System.out.println("Add Person into IOC container");
        return new Person("Bill Gates",64);
    }

    @Conditional({LinuxCondition.class})
    @Bean("Linus")
    public Person person1(){
        return new Person("Linus",49);
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
