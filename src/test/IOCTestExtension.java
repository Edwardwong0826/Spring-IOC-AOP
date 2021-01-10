package test;

import com.spring.configs.SpringConfigOfLifeCycle;
import com.spring.configs.SpringExtensionConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IOCTestExtension {

    @Test
    public void test1()
    {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringExtensionConfig.class);

        // singleton bean destroy method is called when the applicationContext is closed
        applicationContext.close();
    }
}
