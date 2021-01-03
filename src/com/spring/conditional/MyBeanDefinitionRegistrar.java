package com.spring.conditional;


import com.spring.beans.Rainbow;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class MyBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    /**
     *
     * AnnotationMetadata - current class annotation information
     * BeanDefinitionRegistry - BeanDefinition register class
     *          to add required bean to the IOC container: use below
     *          BeanDefinitionRegistry.registerBeanDefinition - manual register
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        boolean red = registry.containsBeanDefinition("com.spring.beans.Red");
        boolean blue = registry.containsBeanDefinition("com.spring.beans.Blue");

        if(red && blue)
        {
            // defined bean information, (bean type, bean scope ...)
            RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(Rainbow.class);

            // register one bean by bean name
            registry.registerBeanDefinition("rainbow",rootBeanDefinition);
        }
    }
}
