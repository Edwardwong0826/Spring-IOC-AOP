package com.spring;

import com.spring.beans.CustomerOld;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

public class Owner {
	
	private String name;
	private List<Pet> list;
	
	@Autowired
	@Qualifier("getCustomerBean1")
	private CustomerOld customer;
	
	public Owner() {
		
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public List<Pet> getlist()
	{
		return this.list;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setlist(List<Pet> list)
	{
		this.list = list;
	}
	
	public String toString()
	{
		return this.name + this.list;
	}
	
	
	
	

}
