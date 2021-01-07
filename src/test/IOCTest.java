package test;

import com.spring.beans.Blue;
import com.spring.beans.Person;
import com.spring.configs.SpringConfig2;
import com.spring.configs.SpringConfig3;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Map;

public class IOCTest {

    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig3.class);


    @Test
    public void testImport()
    {
        printBeans(applicationContext);
        Blue bean = applicationContext.getBean(Blue.class);
        System.out.println(bean);

        //FactoryBean get by getObject instantiate object, it will return proxy object color
        Object colorFactoryBean = applicationContext.getBean("colorFactoryBean");
        // if we want to get the FactoryBean itself, we can add & to get it
        Object colorFactoryBean1 = applicationContext.getBean("&colorFactoryBean");

        System.out.println("Bean type: " +colorFactoryBean.getClass());
        System.out.println("Bean type: " +colorFactoryBean1.getClass());
    }

    @Test
    public void test()
    {
        String[] names = applicationContext.getBeanNamesForType(Person.class);

        // we can get the system OS dynamic value by below
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        String OS = environment.getProperty("os.name");
        System.out.println(OS);

        for(String name: names){
            System.out.println(name);
        }
        Map<String,Person> persons = applicationContext.getBeansOfType(Person.class);
        System.out.println(persons);
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
