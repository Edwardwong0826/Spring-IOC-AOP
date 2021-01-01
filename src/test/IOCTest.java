package test;

import com.spring.beans.Person;
import com.spring.configs.SpringConfig2;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Map;

public class IOCTest {

    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig2.class);

    @Test
    public void test(){
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

}
