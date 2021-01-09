package test;

import com.spring.AOP.Annotation.User;
import com.spring.AOP.Annotation.UserProxy;
import com.spring.configs.SpringConfigOfAOP;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IOCTestAOP {

    @Test
    public void test1()
    {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfigOfAOP.class);

        // only use spring IOC container bean can use AOP aspect functionality
        User user = applicationContext.getBean(User.class);
        user.add();
        user.calculator(1,2);

        applicationContext.close();
    }
}
