package com.spring.extension;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class MyApplicationListener implements ApplicationListener<ApplicationEvent> {

    // when IOC container publish this event after, method trigger
    @Override
    public void onApplicationEvent(ApplicationEvent event)
    {
        System.out.println("Receive event " + event);
    }
}
