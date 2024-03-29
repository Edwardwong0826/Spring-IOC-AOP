package com.spring.beans;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringValueResolver;

@Component
public class Red implements ApplicationContextAware, BeanNameAware, EmbeddedValueResolverAware
{

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        System.out.println("pass in IOC : " + applicationContext);
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("Current bean name : " + name);
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        resolver.resolveStringValue("你好${os.name}我是#{20*18}");
        System.out.println(resolver);
    }

}
