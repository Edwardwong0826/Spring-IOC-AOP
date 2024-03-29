package com.spring.extension;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class UserListenerService {

    @EventListener(classes= {ApplicationEvent.class})
    public void listen(ApplicationEvent event)
    {
        System.out.println("UserService...listen event" + event);
    }
}
