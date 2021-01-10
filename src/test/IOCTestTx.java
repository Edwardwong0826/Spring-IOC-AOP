package test;

import com.spring.configs.SpringOfTxConfig;
import com.spring.tx.UserService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IOCTestTx {

    @Test
    public void test1()
    {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringOfTxConfig.class);
        UserService userService = applicationContext.getBean(UserService.class);
        userService.insertUser();


        applicationContext.close();
    }
}
