package com.rahulhembrom.springsecuritydemo.event.listener;

import com.rahulhembrom.springsecuritydemo.entity.User;
import com.rahulhembrom.springsecuritydemo.event.RegistrationCompleteEvent;
import com.rahulhembrom.springsecuritydemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {
    @Autowired
    private UserService userService;
    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        // create the verification token for the User with link
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        userService.saveVerificationTokenForUser(token,user);
        // Send mail to the user
        String url = event.getApplicationUrl()
                +"/verifyRegistration?token="
                +token;
        // send verification email
        // here we are mocking
        log.info("Click the link to verify the account: {}",url);
    }
}
