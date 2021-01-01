package com.spring.conditional;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

// determine is it linux OS, conditional need to implements Condition interface
public class LinuxCondition implements Condition {

    /**
     *
     * ConditionContext: 判断条件能使用的上下文（环境）
     * AnnotatedTypeMetadata : annotation information
     *
     */
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        // 1. get the ioc current using beanFactory
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();

        // 2. get the class loader
        ClassLoader classLoader = context.getClassLoader();

        // 3. get the current environment information
        Environment env = context.getEnvironment();

        // 4. get bean definition registry class type
        BeanDefinitionRegistry registry = context.getRegistry();

        String property = env.getProperty("os.name");

        // can determine container register bean situation, also give container register bean based on this
        boolean definition =  registry.containsBeanDefinition("iD");
        if(property.contains("Linux")){
            return true;
        }
        return false;
    }
}
