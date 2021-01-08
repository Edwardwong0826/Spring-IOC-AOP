package com.spring.beans;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class Dog implements ApplicationContextAware {

    @Autowired
    private ApplicationContext applicationContext;

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

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
