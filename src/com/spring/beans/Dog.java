package com.spring.beans;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class Dog {

    public Dog()
    {
        System.out.println("Dog...Constructor...");
    }

    // after object is construct will called
    @PostConstruct
    public void init()
    {
        System.out.println("Dog... @PostConstruct...");
    }

    // before container remove the bean
    @PreDestroy
    public void destroy()
    {
        System.out.println("Dog... @PreDestroy...");
    }
}
