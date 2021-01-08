package test;


import com.spring.configs.SpringConfigOfProfile;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;

public class IOCTestProfile {

    // 1. in JVM parameter run configuration -Dspring.profiles.active=test, then @Profile("test") bean will be register
    @Test
    public void test1()
    {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfigOfProfile.class);
        String[] namesForType = applicationContext.getBeanNamesForType(DataSource.class);
        for(String name : namesForType)
        {
            System.out.println(name);
        }

        // 1. set the environment and set profile
        // 2. register to application
        // 3. refresh
        AnnotationConfigApplicationContext testPorfileApplicationContext = new AnnotationConfigApplicationContext();
        testPorfileApplicationContext.getEnvironment().setActiveProfiles("test","dev");
        applicationContext.register(SpringConfigOfProfile.class);
        applicationContext.refresh();


        // singleton bean destroy method is called when the applicationContext is closed
        applicationContext.close();
    }
}
