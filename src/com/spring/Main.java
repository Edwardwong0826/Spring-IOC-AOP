package com.spring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"com.qa.repository","com.qa.beans "})
public class Main implements CommandLineRunner {
	
	//@Autowired
	//private CustomerRepository customerRepository;
		
	public static void main(String[] args)
	{
		
		SpringApplication.run(Main.class, args); 
		
	}

	@Override
	public void run(String... args) throws Exception 
	{
		
		//customerRepository.deleteCustomerById(101);
		
	}

}
