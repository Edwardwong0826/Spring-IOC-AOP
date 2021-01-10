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
 *  4. ApplicationListener: listen container publish event, event driven model develop
 *          public interface ApplicationListener<E extends ApplicationEvent>
 *              listen ApplicationEvent all its subclass event
 *
 *     Step:
 *          1. write one listener to listen some event (ApplicationEvent and its subclass event)
 *             @EventListener; can be added into any method or class to start listen
 *             use EventListenerMethodProcessor to handle @EventListener on method
 *          2. add listener to IOC container
 *          3. once when IOC container have related event publish, we can listen this event
 *                  ContextRefreshedEvent: IOC Container refresh completed (all bean have completed create) will publish
 *                  ContextClosedEvent: closed IOC container will publish
 *          4.publish one event:
 *                  applicationContext.publishEvent()
 *     Principle
 *          ContextRefreshedEvent.IOCTestExtension$1[source="xxxxx"].ContextClosedEvent:
 *        1.ContextRefreshedEvent:
 *          1. IOC container create object, refresh()
 *          2. finishRefresh();
 *          3. publishEvent(new ContextRefreshedEvent(this));
 *              1. getApplicationEventMulticaster() - 派发器
 *              2. multicastEvent 派发事件
 *              3. get all ApplicationListener
 *                  for(final ApplicationListener<?>) listener : getApplicationListeners(event,type)
 *                  1. if have Executor, can use Executor to async cast
 *                  2. else invokeListener(listener, event)
 *                      get listener call back onApplicationEvent method
 *
 *        ApplicationEventMulticaster
 *        1. IOC container create object, refresh()
 *        2. initApplicationEventMulticaster(); initialization event multi caster
 *          1. find from IOC container id = applicationEventMulticaster component
 *          2. don't have new SimpleApplicationEventMulticaster(beanFactory)
 *             and register into beanFactory, 我们就可以在其他component要派发event， auto inject this applicationEventMulticaster
 *
 *        IOC Container got what event listener
 *        1. IOC container create object, refresh()
 *        2. registerListeners();
 *           get all listener from IOC container, and register them insisde to applicationEventMulticaster
 *           String[] listenerBeanNames = getBeanNamesForType(ApplicationListener.class, true, false)
 *           getApplicationEventMulticaster().addApplicationListenerBean(listenerBeanName )
 *
 *        SmartInitializingSingleton
 *        1. IOC container create object, refresh()
 *        2. finishBeanFactoryInitialization(beanFactory);initialize all remaining singleton beans
 *           1. first create all singleton beans: use getBean()
 *           2. get all created singleton bean, determine is it SmartInitializingSingleton type
 *              if yes call afterSingletonsInstantiated()
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
