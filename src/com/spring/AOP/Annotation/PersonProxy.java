package com.spring.AOP.Annotation;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(1) // the number smallest, the more priority
public class PersonProxy {
    // if there is multiple proxy class want to advice on the same class method, we can set the proxy class priority to determined  the order
    @Pointcut(value="execution(* com.spring.AOP.Annotation.User.add(..))")
    public void pointCut(){

    }

    //Advice
    @Before(value="pointCut()")
    public void before(){
        System.out.println("Person Before");
    }
}
