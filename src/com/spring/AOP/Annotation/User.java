package com.spring.AOP.Annotation;

import org.springframework.stereotype.Component;

@Component
public class User {

    public void add(){
        System.out.println("Add........");
    }

    public int calculator(int i, int j)
    {
        System.out.println("Calculator....... i + j");
        return i + j;
    }
}
