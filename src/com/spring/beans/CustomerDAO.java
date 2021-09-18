package com.spring.beans;

import com.spring.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

// be default, if we don't specify the value of component, the spring will auto put it as Class name with first letter smaller,
// the value is the bean id
@Component
//@Component("customerDAO1") // or we can define the bean name value in @Component, when get or scan bean is refer to this instead of auto generated one
@Scope("prototype")
public class CustomerDAO {
	
	@SuppressWarnings("unused")
	private int integer;

	// the @Autowired is dependency injection by the type, so one type of class no problem, if multiple class implements or extends the class
	// it will not know which class bean to select, @Qualifier can solve that, it dependency injection by the bean name, must use with @Autowired
	// together
	@Autowired
	//@Qualifier(value = "xxxx") // @Qualifies is dependency injection by name , like
	private Pet pet;


	public CustomerDAO(Pet pet)
	{
		this.pet = pet;
	}

	//	public void setPet(Pet pet){
	//		this.pet = pet;
	//	}
	//

	public Pet getPet()
	{
		return pet;
	}

	public void setPet(Pet pet) {
		this.pet = pet;
	}

	public int getInteger() {
		return integer;
	}

	public void setInteger(int integer) {
		this.integer = integer;
	}

	@Override
	public String toString() {
		return "CustomerDAO{" +
				"integer=" + integer +
				", pet=" + pet +
				'}';
	}
}
