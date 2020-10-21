package com.spring;

import org.springframework.stereotype.Component;

@Component
public class Pet {
	
	// if use @Autowired, it will go to @Configuration class look the bean for the same name, if cannot find
	// it will return the same type bean, in this case the String is name, so it will go to SpringConfig
	// @Bean
	// public string name method() - to get the value
	//@Autowired
	private String name;
	
	
	public Pet() 
	{
		System.out.println("Execute no args constructor instantiate bean instance");
	}
	
	public Pet(String name)
	{
		
		this.name = name;
		
	} 
	
    public void setName(String name)
	{
		System.out.println("Use Set method setting property value");
    	this.name = name;
    	
	} 
    
	public String getName() 
	{ 
		
		return name;
		
	} 
	
	@Override
	public String toString()
	{
		return this.name;
	}

	public void initMethod(){
		System.out.println("Execute Init Method");
	}

	public void destroyMethod(){
		System.out.println("Execute destroy Method");
	}

}
