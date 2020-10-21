package com.spring;

import com.spring.AOP.Annotation.User;
import com.spring.beans.Customer;
import com.spring.beans.CustomerDAO;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication // component scan can scan those class mark with any @Component, @Controller, @Service, @Repository as bean
public class SpringBeanComponentScanApp {

    public static void main(String[] args){


        ApplicationContext contextComponentScan = new AnnotationConfigApplicationContext(SpringBeanComponentScanApp.class);
        Customer customer =  contextComponentScan.getBean(Customer.class);
        CustomerDAO customerDAO =  contextComponentScan.getBean("customerDAO",CustomerDAO.class);
        CustomerDAO customerDAO1 =  contextComponentScan.getBean("customerDAO",CustomerDAO.class);
        Pet pet = contextComponentScan.getBean(Pet.class);
        System.out.println("Get instantiate bean instance object");

        pet.setName("Dog");

        System.out.println(customer.getName());
        System.out.println(customerDAO.toString());

        // by default the bean are default to singleton scope, we can set the bean to different scope we want
        // -singleton
        // -prototype
        // -request
        // -session
        // -application
        // -websocket
        System.out.println(customerDAO.getClass() + ":" + customerDAO.hashCode());
        System.out.println(customerDAO1.getClass() + ":" + customerDAO1.hashCode());

        // Aop advice before
        User user = contextComponentScan.getBean("user", User.class);
        user.add();
    }

}

