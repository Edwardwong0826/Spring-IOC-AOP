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
 * AOP Creation
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
 *  3. method execution
 *      IOC container save dynamic proxy (cglib enhances object), in this object save all the information(advisor,  target object, xxx)
 *      1. CglibAopProxy.intercept; - intercept target method execution
 *      2. according ProxyFactory object obtain 即将执行 target method interceptor chain:
 *         List<Object> chain = this.advised.getInterceptorAndDynamicInterceptionAdvice(method, targetClass)
 *         1.List<Object> interceptorList = new ArrayList<Object>(config.getAdvisors.length)
 *           inside length is 7, 6 from the advice method in User class, one is default ExposeInvocationInterceptor pass by spring
 *         2.traverse all advisor, change and add to interceptor
 *           registry.getInterceptors(advisor);
 *         3.change advisor to List<MethodInterceptor>: - some advisor is not implements MethodInterceptor
 *           if is MethodInterceptor, direct add to List
 *           if not, use AdvisorAdapter change advisor to MethodInterceptor
 *           when change complete, return MethodInterceptor array
 *
 *      3. if don't have interceptor chain, invoke target method
 *         interceptor chain(every advice that is wrapper to MethodInterceptor, use MethodInterceptor mechanism)
 *      4. if got interceptor chain, pass in execute target Object, target method, interceptor chain to create CglibMethodInvocation() object,
 *          and call CglibMethodInvocation(proxy, target, method, args, targetClasses, methodProxy).proceed() to return Object type called retVal
 *          Object retVal = CglibMethodInvocation(xxx).proceed() t
 *      5. interceptor chain trigger flow
 *          1. if don't have interceptor direct execute target method, or interceptor index and interceptor array -1 is the same (point to last interceptor) execute target method
 *          2. chain get every interceptor, interceptor execute invoke() method, every interceptor wait next interceptor run and return only execute
 *             interceptor chain mechanism, to ensure advice method and target method execution order
 *
 *  Conclusion
 *      1. @EnableAspectJAutoProxy open AOP function
 *      2. @EnableAspectJAutoProxy will register one component AnnotationAwareAspectJAutoProxyCreator to IOC container
 *      3. AnnotationAwareAspectJAutoProxyCreator is a BeanPostProcessor
 *      4. IOC Container Life cycle
 *          1. register BeanPostProcessors() create AnnotationAwareAspectJAutoProxyCreator object
 *          2. finishBeanFactoryInitialization() after initialization singleton bean
 *              1. create logic component and aspect component
 *              2. AnnotationAwareAspectJAutoProxyCreator will intercept component creation
 *              3. component after created completed, determine component is it required advice
 *                  yes: aspect advice method, wrap to Advisor; create logic component proxy dynamic object
 *      5. target method invoke
 *          1. dynamic proxy object invoke target method
 *          2. CglibAopProxy.intercept():
 *              1. get target method interceptor chain(advisor wrap to interceptor)
 *              2. use interceptor chain mechanism, 以此进入每一个interceptor to invoke/ execute
 *              3. result:
 *                 Normal execution : @Before advice -> target method ->@After advice -> @AfterReturning advice
 *                 Exception execution: @Before advice -> target method ->@After advice -> @AfterThrowing advice
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
