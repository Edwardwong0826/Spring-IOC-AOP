package com.spring.configs;

import com.spring.beans.Color;
import com.spring.beans.Person;
import com.spring.beans.Red;
import com.spring.conditional.LinuxCondition;
import com.spring.conditional.MyBeanDefinitionRegistrar;
import com.spring.conditional.MyImportSelector;
import com.spring.conditional.WindowsCondition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


@Configuration
// import the class to IOC, default id is full class name
@Import({Color.class, Red.class, MyImportSelector.class, MyBeanDefinitionRegistrar.class})
public class SpringConfig3 {


    /**
     * @Conditional can based on condition to determine, if true then register the bean into IOC container
     * example if system is windows, then add Bill to containe
     * if system is linus, then add Linus to container
     */
    @Conditional({WindowsCondition.class})
    @Bean("Bill")
    public Person person()
    {
        System.out.println("Add Person into IOC container");
        return new Person("Bill Gates",64);
    }

    @Conditional({LinuxCondition.class})
    @Bean("Linus")
    public Person person1()
    {
        return new Person("Linus",49);

    }

    /**
     *  1. @ComponentScan + annotation (@Controller/@Service/@Repository/@Component) - this is 被动 add to IOC
     *  2. @Bean - import third party class
     *  3. @Import - quickly import class to IOC container
     *     1. @Import - this is we 主动 add to IOC container, if the class or third party class didn't add @Controller, @Service those cannot be scan by @ComponentScan etc
     *     2. ImportSelector -  return needed import classes all class name string
     *     3. ImportBeanDefinitionRegistrar - manual register bean to IOC container
     */

}
