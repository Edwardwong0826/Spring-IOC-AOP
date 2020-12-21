package com.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class SpringConfig1 {
	
	@Bean
	@Scope("prototype")
	public Pet pet1Bean()
	{ 
		Pet pet = new Pet(); 
		pet.setName("Daisy");   
		return pet; 
	} 

	//@Bean register a bean into IOC container, type is the return type of method, and the id is default to use method name
	// if we dont have use method name as id, we can enter value to define the bean name and use that as id
    @Bean(value = "pet2Bean1")
    public Pet pet2Bean()
	{  
		return new Pet("Lola"); 
	} 
    
    @Bean
    public String name()
    {
    	return "haha";
    }
    
    @Bean
    public Owner getOwnerBean()
    {
    	Owner owner = new Owner();
    	owner.setName("Wong");
    	List<Pet> pets = new ArrayList<>();
    	pets.add(pet1Bean());
    	pets.add(pet2Bean());
    	owner.setlist(pets);
    	return owner;
    }


	// Spring singletons is “per container per bean”, means create instances in the container bind to their id,
	// while Singleton Pattern ensures one instance of a particular class of per class loader, means one class will only have one instance
	//@Bean(name = "CustomerDAO")
//    @Bean
//    public CustomerDAO getCustomerDAO(@Autowired @Qualifier("pet2Bean")Pet pet) {
//
//
//    	return new CustomerDAO(pet);
//
//    }


    
}
