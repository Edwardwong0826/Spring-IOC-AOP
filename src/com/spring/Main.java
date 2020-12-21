package com.spring;

import com.spring.beans.Customer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Component;

@SpringBootApplication
@ComponentScan(basePackages={"com.spring","com.qa.repository","com.qa.beans"},includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION,
		classes = Component.class), useDefaultFilters = false)
//@ComponentScan(basePackages={"com.spring","com.qa.repository","com.qa.beans "},excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION,
//		classes = Component.class), useDefaultFilters = false)
// includeFilters, only include those want to scan
// excludeFilters, exclude those don't want to scan
public class Main implements CommandLineRunner {
	
	//@Autowired
	//private CustomerRepository customerRepository;

	private static ApplicationContext applicationContext;
		
	public static void main(String[] args)
	{
		//SpringApplication.run(Main.class, args);
		applicationContext = SpringApplication.run(Main.class, args);
		checkBeansPresence(
				"pet", "owner", "customer", "mainExampleCustomerBean", "main", "CustomerOld");
		
	}


	@Bean(value= "mainExampleCustomerBean")
	public Customer exampleCustomerBean() {
		return new Customer();
	}

	@Override
	public void run(String... args) throws Exception 
	{
		
		//customerRepository.deleteCustomerById(101);
		
	}


	private static void checkBeansPresence(String... beans) {
		for (String beanName : beans) {
			System.out.println("Is " + beanName + " in ApplicationContext: " +
					applicationContext.containsBean(beanName));
		}
	}

}
