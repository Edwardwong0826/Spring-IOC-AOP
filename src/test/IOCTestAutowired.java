package test;

import com.spring.configs.SpringConfigOfAutowired;
import com.spring.service.BookService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IOCTestAutowired {

    @Test
    public void test1()
    {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfigOfAutowired.class);
        System.out.println("IOC Container create complete ");

        BookService bookService = applicationContext.getBean(BookService.class);
        System.out.println(bookService);

        // singleton bean destroy method is called when the applicationContext is closed
        applicationContext.close();
    }
}
