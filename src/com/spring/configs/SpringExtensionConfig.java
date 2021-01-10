package com.spring.configs;

import com.spring.beans.Blue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 *
 *
 *  Spring Extension principle:
 *      BeanPostProcessor: when bean create object and initialization before after intercept operation
 *      BeanFactoryPostProcessor:
 *          in beanFactory after standard initialization to call: all bean definition already save and loaded to beanFactory, but bean instance is not instantiate yet
 *
 *  1. IOC container create object
 *  2. invokeBeanFactoryPostProcessors(beanFactory); execute this
 *      how this find all BeanFactoryPostProcessor and execute their methods
 *      1. directly in BeanFactory find all type is BeanFactoryPostProcessor component and execute their methods
 *      2. in initialization create other component executed
 *
 *  3. BeanDefinitionRegistryPostProcessor extends BeanFactoryPostProcessor
 *          postProcessBeanDefinitionRegistry();
 *          when all bean definition will have been loaded and bean instance have not create
 *
 *          priority than BeanFactoryPostProcessor when executed,
 *          can use BeanDefinitionRegistryPostProcessor too add extra component
 *    Principle
 *          1. IOC container create object
 *          2. refresh() -> invokeBeanFactoryPostProcessors()
 *          3. get from IOC container all BeanDefinitionRegistryPostProcessor component, trigger all postProcessBeanDefinitionRegistry() method
 *              1. 再来触发postProcessBeanFactory()方法BeanFactoryPostProcessor
 *
 *          4.再来从IOC container find BeanFactoryPostProcessor component, 然后依次触发postProcessBeanFactory() method
 *
 *
 */
@ComponentScan("com.spring.extension")
@Configuration
public class SpringExtensionConfig {

    @Bean
    public Blue blue()
    {
        return new Blue();
    }
}
