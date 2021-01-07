package com.spring.configs;

import com.spring.repository.BookDAO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * Auto Configuration
 *      Spring use dependency injection to complete IOC container every component dependency relationship value assign
 * 1. @Autowired: spring specification to auto inject
 *    1. default by type go to IOC container to inject
 *    2. if found multiple same type component, will search as reference name to IOC container find
 *    3. @Qualifier, use this defined which bean id to be inject instead of use the reference name
 *    4. by default the must be have instance to be inject to the autowired reference, it cannot be null
 *       , if want to able to be null can in @Autowired(required=false)
 *    5. @Primary, this can make spring default primary choose the bean to be inject
 *  2. @Resource(JSR250) and @Inject(JSR330) is java specification , @Inject is removed after JDK 11
 */
@ComponentScan({"com.spring.service","com.spring.controller","com.spring.repository"})
public class SpringConfigOfAutowired {

    @Bean("bookDAO2")
    public BookDAO bookDao()
    {   BookDAO bookDAO = new BookDAO();
        bookDAO.setLabel("2");
        return bookDAO;
    }
}
