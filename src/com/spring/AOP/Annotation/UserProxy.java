package com.spring.AOP.Annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

//Aspect is the proxy class for advice
@Component
@Aspect // generate proxy object
@Order(2)
public class UserProxy {


    @Pointcut(value="execution(* com.spring.AOP.Annotation.User.add(..))")
    public void pointCut(){

    }

    //Advice
    @Before(value="pointCut()")
    public void before(){
        System.out.println("Before");
    }

    @AfterReturning(value="execution(* com.spring.AOP.Annotation.User.add(..))")
    public void afterReturning(){
        System.out.println("AfterReturning");
    }

    // this is finally advice, no matter got exception in the method also will execute this advice
    @After(value="execution(* com.spring.AOP.Annotation.User.add(..))")
    public void After(){
        System.out.println("After");
    }

    @AfterThrowing(value="execution(* com.spring.AOP.Annotation.User.add(..))")
    public void afterThrowing(){
        System.out.println("AfterThrowing");
    }

    @Around(value="execution(* com.spring.AOP.Annotation.User.add(..))")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable{
        System.out.println("Before Around");
        joinPoint.proceed();
        System.out.println("After Around");
    }

}
