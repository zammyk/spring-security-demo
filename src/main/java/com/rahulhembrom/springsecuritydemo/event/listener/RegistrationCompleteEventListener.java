package com.rahulhembrom.springsecuritydemo.event.listener;

import com.rahulhembrom.springsecuritydemo.entity.User;
import com.rahulhembrom.springsecuritydemo.event.RegistrationCompleteEvent;
import org.springframework.context.ApplicationListener;

import java.util.UUID;

public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {
    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        // create the verification token for the User with link
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        // Send mail to the user
    }
}
