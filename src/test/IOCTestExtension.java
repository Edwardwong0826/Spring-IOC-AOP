package test;

import com.spring.configs.SpringExtensionConfig;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IOCTestExtension {

    @Test
    public void test1()
    {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringExtensionConfig.class);

        // now publish
        applicationContext.publishEvent(new ApplicationEvent(new String("我发布的时间")) {
        });

        // singleton bean destroy method is called when the applicationContext is closed
        applicationContext.close();
    }
}
