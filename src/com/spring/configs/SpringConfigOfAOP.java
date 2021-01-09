package com.spring.configs;

import com.spring.AOP.Annotation.User;
import com.spring.AOP.Annotation.UserProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * AOP principle
 *     1.@EnableAspectJAutoProxy
 *          @Import (AspectJAutoProxyRegistrar.class), import to IOC AspectJAutoProxyRegistrar and defined registrar to register bean into IOC
 *          internalAutoProxyCreator = AnnotationAwareAspectJAutoProxyCreator
 *       register to IOC one AnnotationAwareAspectJAutoProxyCreator:
 *
 *     2.AnnotationAwareAspectJAutoProxyCreator
 *         ->AnnotationJAwareAdvisorAutoProxyCreator
 *            ->AbstractAdvisorAutoProxyCreator
 *                ->AbstractAutoProxyCreator
 *                      implements SmartInstantiationAwareBeanPostProcessor, BeanFactoryAware
 *                      BeanPostProcessor (in bean initialization before and after do something), auto inject BeanFactory
 *
 *   AbstractAutoProxyCreator.setBeanFactory()
 *   inside got BeanPostProcessor override logic, need to check
 *
 *   AbstractAdvisorAutoProxyCreator.setBeanFactory() -> initBeanFactory()
 *   AnnotationJAwareAdvisorAutoProxyCreator.initBeanFactory
 *
 *   Flow:
 *      1. import @configuration class, create IOC Container
 *      2. registry configuration class, call refresh() refresh IOC Container (line init Container);
 *      3. registerBeanPostProcessors(beanFactory) to intercept bean creation
 *          1. get IOC container already define and needed to create object all BeanPostProcessor
 *          2. add other BeanPostProcessor to IOC container
 *          3. priority registry those PriorityOrdered interface BeanPostProcessor
 *          4. IOC container registry those implements Ordered interface BeanPostProcessor
 *          5. registry no priority BeanPostProcessor
 *          6. registry BeanPostProcessor, actually is create BeanPostProcessor object and save into IOC container
 *              create internalAutoProxyCreator BeanPostProcessor[AnnotationAwareAspectJAutoProxyCreator]
 *              1. create bean instance
 *              1. populateBean: assign value to bean properties/fields
 *              3. initializeBean
 *                  1. invokeAwareMethods(): handle Aware interface to callback method to return
 *                  2. applyBeanPostProcessorsBeforeInitialization(): call BeanPostProcessor BeforeInitialization method
 *                  3. invokeInitMethods():
 *                  4. applyBeanPostProcessorsAfterInitialization() : call BeanPostProcessor AfterInitialization method
 *              4. BeanPostProcessor(AnnotationAwareAspectJAutoProxyCreator) success create: --> aspectJAdvisorBuilder
 *          7. beanFactory.addBeanPostProcessor(postProcessor); add to beanFactory
 * ======= above is create and register AnnotationAwareAspectJAutoProxyCreator process ===============
 *          AnnotationJAwareAdvisorAutoProxyCreator => InstantiationAwareBeanPostProcessor
 *
 * AOP execution
 *  AnnotationAwareAspectJAutoProxyCreator[InstantiationAwareBeanPostProcessor] 作用:
 *  1. in every bean before instantiate, call postProcessBeforeInstantiation()
 *      关心 User and UserProxy creation
 *      1. determine bean is it in advisedBeans(keep all needed advised/enhanced bean)
 *      2. determine bean is it is Infrastructure or Advice, Advisor, PointCut, AopInfrastructureBean or is Aspect(@Aspect
 *      3. is it shouldSkip
 *          1. get candidate advisor (in aspect method those @Before @After...) [List<Advisor> candidateAdvisors]
 *             每一个封装advice method is InstantiationModelAwarePointCutAdvisor
 *             determine every advisor is it AspectJPointCutAdvisor type, if yes return true
 *          2. return false
 *  2. create object
 *  postProcessAfterInitialization:
 *      return wrapIfNecessary(bean, beanName, cacheKey);// wrap if needed
 *      1. get current bean all advisor
 *          1. find candidate advisor from the current bean that can use(find which advice method is to point cut to current bean method)
 *          2. get eligible advisor that can use on current bean
 *          3. sort the eligible advisor
 *      2. save current bean in advisedBeans
 *      3.if bean need advice, create current bean dynamic proxy object
 *          1. get all advisor(advice method)
 *          2. save to proxyFactory
 *          3. create proxy object, spring can decide
 *             JDKDynamicAopProxy(config); jdk dynamic proxy
 *             ObjenesisCglibAopProxy(config); cglib dynamic proxy
 *      4. return to IOC container current component use cglib enhancer dynamic proxy object
 *      5. 以后IOC container get is this component dynamic proxy object, when executed method, dynamic proxy object will execute advice method flow
 *
 */

@EnableAspectJAutoProxy
@Configuration
public class SpringConfigOfAOP {

    @Bean
    public User user()
    {
        return new User();
    }

    @Bean
    public UserProxy userProxy()
    {
        return new UserProxy();
    }
}
