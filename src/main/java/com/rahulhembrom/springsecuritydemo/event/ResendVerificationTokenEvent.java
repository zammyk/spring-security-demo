package com.rahulhembrom.springsecuritydemo.event;

import com.rahulhembrom.springsecuritydemo.entity.User;
import com.rahulhembrom.springsecuritydemo.entity.VerificationToken;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;
@Getter
@Setter
public class ResendVerificationTokenEvent extends ApplicationEvent {
    private User user;
    private String applicationUrl;
    private VerificationToken verificationToken;
    public ResendVerificationTokenEvent(User user, String applicationUrl, VerificationToken verificationToken) {
        super(user);
        this.user = user;
        this.applicationUrl = applicationUrl;
        this.verificationToken = verificationToken;
    }
}
