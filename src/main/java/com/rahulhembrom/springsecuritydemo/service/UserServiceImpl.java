package com.rahulhembrom.springsecuritydemo.service;

import com.rahulhembrom.springsecuritydemo.entity.User;
import com.rahulhembrom.springsecuritydemo.model.UserModel;
import com.rahulhembrom.springsecuritydemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Override
    public User registerUser(UserModel userModel) {
        User user = new User();
        user.setEmail(userModel.getEmail());
        user.setRole("USER");
        user.setFirstName(userModel.getFirstName());
        user.setLastName(userModel.getLastName());
        user.setPassword(userModel.getPassword());
        userRepository.save(user);
        return user;
    }
}
