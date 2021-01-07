package test;

import com.spring.configs.SpringConfigOfLifeCycle;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class IOCTestLifeCycle {

    @Test
    public void test1()
    {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfigOfLifeCycle.class);
        System.out.println("IOC Container create complete ");


        // singleton bean destroy method is called when the applicationContext is closed
        applicationContext.close();
    }

}
