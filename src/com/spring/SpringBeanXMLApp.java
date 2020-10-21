package com.spring;

import com.spring.beans.CustomerDAO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringBeanXMLApp {

    public static void main(String[] args)
    {
        // below are two IOC container interface class that use to get the xml beans, but normally we use the ApplicationContext,
        // the FactoryBean are more basic that not recommended use, ApplicationContext will have more features
        // FactoryBean context = new ClassPathXmlApplicationContext("bean1.xml");;

        // Spring bean managed by Spring IOC based on the XML or Java configuration. The Spring beans creation, management,
        //        // and disposal handled by the Spring IoC. At a high-level Spring Bean pass through a different life cycle.

        //  -Execute no args constructor instantiate bean instance
        //  -Populate properties: Spring IoC container injects the bean’s properties.
        //  -Post Initialize: Spring container calls their postProcessBeforeInitialization() method.
        //  -Execute bean init method , need to go the config file to set
        //  -Post Initialize: Spring container calls their postProcessAfterInitialization() method
        //  -Get the bean and use the bean
        //  -If a bean is no longer required, the container will remove it.
        ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml");
        Pet pet = context.getBean("Pet1",Pet.class);
        pet.setName("First");
        System.out.println(pet);

        CustomerDAO customer = context.getBean("CustomerDAO1",CustomerDAO.class); // this is constructor injection based
        //CustomerDAO customer2 = context.getBean("CustomerDAO2",CustomerDAO.class); // this setter based injection based
        System.out.println(customer.getPet());
        customer.getPet().setName("Dog");
        System.out.println(customer.getPet().getName());
        System.out.println(customer.getPet());

        // only container close it will go to bean to call destroy method to remove bean from container
        ((ClassPathXmlApplicationContext)context).close();

        //System.out.println(customer2.getPet().getName());

    }
}
