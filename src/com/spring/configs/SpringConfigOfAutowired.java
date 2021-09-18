package com.spring.configs;

import com.spring.beans.Car;
import com.spring.beans.Color;
import com.spring.repository.BookDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * Auto Configuration
 *      Spring use dependency injection to complete IOC container every component dependency relationship value assign
 * 1. @Autowired: spring specification to auto-inject
 *    1. default by type go to IOC container to inject
 *    2. if found multiple same type component, will search as reference name to IOC container find
 *    3. @Qualifier, use this defined which bean id to be injected instead of use the reference name
 *    4. by default the must-have instanced to be injected to the autowired reference, it cannot be null
 *       , if want to be able to be null can in @Autowired(required=false)
 *    5. @Primary, this can make spring default primary choose the bean to be injected
 *  2. @Resource(JSR250) and @Inject(JSR330) is java specification , @Inject is removed after JDK 11
 *  AutowiredAnnotationBeanPostProcessor : analyse to complete auto-inject function
 *
 *  3. @Autowired : constructor, method, fields, all get from IOC container
 *    1. mark at constructor if only got one parameter constructor and can be omitted
 *    2. mark at set method
 *    3. mark at method parameter
 *  4. own defined class want to use spring lower level class (ApplicationContext, BeanFactory)
 *     by xxxAware, Aware interface, will use interface method to inject own defined bean
 *     xxxAware: function is achieve by xxxProcessor
 *     like ApplicationContextAware => ApplicationContextAwareProcessor
 */
@ComponentScan({"com.spring.service","com.spring.controller","com.spring.repository","com.spring.beans"})
public class SpringConfigOfAutowired {

    @Bean("bookDAO2")
    public BookDAO bookDao()
    {   BookDAO bookDAO = new BookDAO();
        bookDAO.setLabel("2");
        return bookDAO;
    }

    /**
     * @Bean method parameter value also got from IOC container
     * @param car
     * @return
     */
    @Bean
    public Color color(@Autowired Car car){
        Color color = new Color();
        color.setCar(car);
        return color;
    }
}
