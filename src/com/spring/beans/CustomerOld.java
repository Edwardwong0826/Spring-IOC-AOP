package com.spring.beans;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class CustomerOld implements InitializingBean, DisposableBean {

	
	//@Autowired need be on each variable
	
	//@Autowired
	private int ID;
	
	//@Autowired
	private String name;
	
	
	//@Autowired
    public void setName(String name)
	{
    	
    	this.name = name;
    	
	} 
	
	
	// setter method auto wiring will go to @Configuration class find bean name with the argument
	// same parameter name and type,  else will get the same type bean
    //@Autowired
    public void setID(int ID)
	{
    	
    	this.ID = ID;
    	
	} 
	
	@Override
	public String toString() 
	{
		return this.ID + this.name;
	}
	
	@Override
	public void destroy() throws Exception
	{
		System.out.println("destroy");
	}

	@Override
	public void afterPropertiesSet() throws Exception
	{
		
		System.out.println("afterPropertiesSet");
		
	}
	

}
