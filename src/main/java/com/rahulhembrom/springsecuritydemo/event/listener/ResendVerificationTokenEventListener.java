package com.rahulhembrom.springsecuritydemo.event.listener;

import com.rahulhembrom.springsecuritydemo.event.ResendVerificationTokenEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ResendVerificationTokenEventListener implements ApplicationListener<ResendVerificationTokenEvent> {
    @Override
    public void onApplicationEvent(ResendVerificationTokenEvent event) {
        String url = event.getApplicationUrl()
                +"/verifyRegistration?token="
                +event.getVerificationToken().getToken();
        log.info("Click the link to verify the account: {}",url);
    }
}
