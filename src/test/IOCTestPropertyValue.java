package test;

import com.spring.beans.Person;
import com.spring.configs.SpringConfigOfPropertyValues;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

public class IOCTestPropertyValue {

    @Test
    public void test1()
    {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfigOfPropertyValues.class);
        System.out.println("IOC Container create complete ");
        printBeans(applicationContext);
        System.out.println("--------------------");

        // can also in applicationContext to getEnvironment and get config property file value by its key
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        String propertyValue = environment.getProperty("person.nickName");

        Person person = (Person)applicationContext.getBean("person");
        System.out.println(propertyValue);
        System.out.println(person);


        // singleton bean destroy method is called when the applicationContext is closed
        applicationContext.close();
    }

    private void printBeans(AnnotationConfigApplicationContext applicationContext)
    {
        String[] definitionNames = applicationContext.getBeanDefinitionNames();
        for(String name: definitionNames)
        {
            System.out.println(name);
        }
    }

}
