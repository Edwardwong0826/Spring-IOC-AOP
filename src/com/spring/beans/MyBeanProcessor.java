package com.spring.beans;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * in bean initialization before and after process work
 */
@Component
public class MyBeanProcessor  implements BeanPostProcessor
{
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException
    {
        System.out.println("postProcessBeforeInitialization..." + beanName + "->" + beanName );
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException
    {
        System.out.println("postProcessAfterInitialization..." + beanName + "->" + beanName );
        return bean;
    }
}
