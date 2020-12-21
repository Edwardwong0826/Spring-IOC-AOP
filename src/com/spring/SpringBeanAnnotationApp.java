package com.spring;

import com.spring.beans.CustomerOld;
import com.spring.configs.SpringConfig2;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.HashMap;

public class SpringBeanAnnotationApp {
	
	public static void main(String[] args)
	{
			
		// below getBean is getting the bean by parameter name, we can passed multiply @Configuration class 
		// into one context to use it, use this way is not the component scan, it will not get the bean by the class mark with @component
		ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig1.class,SpringConfig2.class);
		//ApplicationContext context2 = new AnnotationConfigApplicationContext(SpringConfig2.class);

		Pet pet1 = (Pet) context.getBean("pet1Bean");
		Pet pet2 = (Pet) context.getBean("pet2Bean1");
		//CustomerDAO customerDAO =  (CustomerDAO)context.getBean("getCustomerDAO"); // is the same as above, not sure why, confuse
		
		System.out.println(pet1.getName());  
		System.out.println(pet2.getName());

		HashMap<String, Pet> list = new HashMap<String, Pet>(context.getBeansOfType(Pet.class));
		
		System.out.println("using a map");
		for (String key : list.keySet())                     
			System.out.println(list.get(key).getName());
		
		System.out.println("using a list");
		Owner owner = (Owner) context.getBean("getOwnerBean");
		System.out.println(owner.toString());
		
		CustomerOld customer = (CustomerOld) context.getBean("getCustomerBean1");
		System.out.println(customer);
		
		((ConfigurableApplicationContext) context).close();
	}

}
