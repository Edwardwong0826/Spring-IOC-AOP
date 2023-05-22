package com.spring.AOP.Annotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 *  AOP
 *   - Spring has two ways of doing AOP, if the class implements an interface it can use a standard JDK Proxy,
 *     if the class does not implement an interface it will create a new subclass by using CGLib
 *     https://stackoverflow.com/questions/10664182/what-is-the-difference-between-jdk-dynamic-proxy-and-cglib
 *     https://stackoverflow.com/questions/42411642/why-can-not-get-annotation-from-beanclass
 *   - CGLib proxy created by Spring will be formatted like <target class>$$EnhancerBySpringCGLIB$$<hex string>
 *   - need to add AOP class and logic class to IOC container
 *   - need to tell spring which class is aspect class, add @Aspect to indicate the class is aspect class
 *   - in aspect class method mark advice annotation, to tell spring when to invoke AOP (by pointcut expression)
 *   - add @EnableAspectJAutoProxy in @Configuration class or together with @SpingBootApplication main class, to activate AOP annotation based 
 */
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

    // normal execution after
    @AfterReturning(value="execution(* com.spring.AOP.Annotation.User.add(..))")
    public void afterReturning(){
        System.out.println("AfterReturning");
    }

    @AfterReturning(value="execution(* com.spring.AOP.Annotation.User.calculator(..))", returning = "result")
    public void afterReturningCalculator(JoinPoint joinPoint,Object result){ //JoinPoint parameter can be first parameter, if not spring cannot recognise
        // jointPoint method can get method name, args name etc
        System.out.println("AfterReturning " + joinPoint.getSignature().getName() + " : return value i + j : " + result);
    }

    // this is finally advice, no matter got exception in the method also will execute this advice
    @After(value="execution(* com.spring.AOP.Annotation.User.add(..))")
    public void After(){
        System.out.println("After");
    }

    // throw when execution
    @AfterThrowing(value="execution(* com.spring.AOP.Annotation.User.add(..))")
    public void afterThrowing(){
        System.out.println("AfterThrowing");
    }

    // before and after also execution
    @Around(value="execution(* com.spring.AOP.Annotation.User.add(..))")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable{
        System.out.println("Before Around");
        joinPoint.proceed();
        System.out.println("After Around");
    }

}
