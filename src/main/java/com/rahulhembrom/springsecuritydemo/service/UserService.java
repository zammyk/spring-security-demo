package com.rahulhembrom.springsecuritydemo.service;

import com.rahulhembrom.springsecuritydemo.entity.User;
import com.rahulhembrom.springsecuritydemo.model.UserModel;

public interface UserService {
    User registerUser(UserModel userModel);
}
