package com.spring.extension;

import com.spring.beans.Blue;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.*;
import org.springframework.stereotype.Component;

@Component
public class MyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("MyBeanDefinitionRegistryPostProcessor...bean number:" + beanFactory.getBeanDefinitionCount());
    }

    // BeanDefinitionRegistry bean definition的保存中心，以后BeanFactory就是按照BeanDefinitionRegistry里面保存的每一个bean定义信息创建bean instance
    // bean定义信息 (bean id是什么，单例还是多实列，bean的类型是什么)
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        System.out.println("postProcessBeanDefinitionRegistry...bean number:"+registry.getBeanDefinitionCount());

        // two way to create BeanDefinition, is the same
        RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(Blue.class);
        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.rootBeanDefinition(Blue.class).getBeanDefinition();

        registry.registerBeanDefinition("hello", rootBeanDefinition);
    }

}
