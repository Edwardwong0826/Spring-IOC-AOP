package com.spring.configs;

import com.spring.beans.Car;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 *  Spring Bean Life Cycle
 *  constructor(object instantiate)
 *      singleton bean: when IOC container is created then instantiate object
 *      multi instance bean: every time when get or request instantiate object
 *  initialization
 *      when object is instantiate and assign value to field/property called initMethod
 *  destroy
 *      singleton bean: when IOC container is closed, the singleton bean called the destroyMethod
 *      multi instance bean: does not call destroyMethod, need our self to handle it, the IOC Container will not handle this
 *                           kind of bean
 *
 * BeanPostProcessor principle
 * Traverse container every BeanPostProcessors, each one execute BeforeInitialization.
 * once return null, exit for loop and will not execute BeanPostProcessors
 * populateBean(xxx) -  assign value to bean object properties
 * {
 *   applyBeanPostProcessorsBeforeInitialization(xxx)
 *   invokeInitMethods(xxx) - executed initialization
 *   applyBeanPostProcessorsAfterInitialization(xxx)
 * }
 *  1. can point to initMethod and destroyMethod
 *      use @Bean initMethod to set initialization and destroyMethod to set destroy
 *  2. use Bean implements InitializingBeanBean interface (defined own  bean init logic),
 *     implements disposableBean interface (defined own bean destroy logic)
 *  3. use JSR250 specification
 *     @PostConstructor
 *     @PreDestroy : when container destroy bean before, will call us to define clean job
 *  4. BeanPostProcessor
 *      in bean initialization before and after process something
 *      postProcessBeforeInitialization() - call before bean initialization
 *      postProcessAfterInitialization() - call after bean initialization
 *
 *  Spring lower level largely use BeanPostProcessor,
 *  like bean assign value to property, inject other component, @Autowired, life cycle annotation function
 */
@ComponentScan("com.spring.beans")
@Configuration
public class SpringConfigOfLifeCycle {


    @Bean(initMethod = "init", destroyMethod = "destroy")
    public Car car(){
        return new Car();
    }
}
