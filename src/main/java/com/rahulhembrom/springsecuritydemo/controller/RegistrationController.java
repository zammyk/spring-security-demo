package com.rahulhembrom.springsecuritydemo.controller;

import com.rahulhembrom.springsecuritydemo.entity.User;
import com.rahulhembrom.springsecuritydemo.event.RegistrationCompleteEvent;
import com.rahulhembrom.springsecuritydemo.model.UserModel;
import com.rahulhembrom.springsecuritydemo.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
public class RegistrationController {
    @Autowired
    private UserService userService;
    @Autowired
    private ApplicationEventPublisher publisher;

//    @GetMapping("/hello")
//    public String helloWorld()
//    {
//        return "Hello World!";
//    }

    @PostMapping("/register")
    public String registerUser(@RequestBody UserModel userModel, final HttpServletRequest request){
        User user = userService.registerUser(userModel);
        publisher.publishEvent(new RegistrationCompleteEvent(user,getApplicationUrl(request)));
        return "Success";
    }

    @GetMapping("/verifyRegistration")
    public String validateVerificationToken(@RequestParam("token") String token){
        String verificationResponse = userService.validateVerificationToken(token);
        return verificationResponse;
    }

    private String getApplicationUrl(HttpServletRequest request) {
        return "http://"
                + request.getServerName()
                + ":"
                + request.getServerPort()
                + request.getContextPath();
    }
}
