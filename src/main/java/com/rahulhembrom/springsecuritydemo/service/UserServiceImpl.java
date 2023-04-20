package com.rahulhembrom.springsecuritydemo.service;

import com.rahulhembrom.springsecuritydemo.entity.User;
import com.rahulhembrom.springsecuritydemo.entity.VerificationToken;
import com.rahulhembrom.springsecuritydemo.model.UserModel;
import com.rahulhembrom.springsecuritydemo.repository.UserRepository;
import com.rahulhembrom.springsecuritydemo.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private VerificationTokenRepository verificationTokenRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public User registerUser(UserModel userModel) {
        User user = new User();
        user.setEmail(userModel.getEmail());
        user.setRole("USER");
        user.setFirstName(userModel.getFirstName());
        user.setLastName(userModel.getLastName());
        user.setPassword(passwordEncoder.encode(userModel.getPassword()));
        userRepository.save(user);
        return user;
    }

    @Override
    public void saveVerificationTokenForUser(String token, User user) {
        VerificationToken verificationToken = new VerificationToken(user, token);
        verificationTokenRepository.save(verificationToken);
    }

    @Override
    public String validateVerificationToken(String token) {
        Optional<VerificationToken> optionalVerificationToken = verificationTokenRepository.findByToken(token);
        if(optionalVerificationToken.isPresent())
        {
            VerificationToken verificationToken = optionalVerificationToken.get();
            Calendar calendar = Calendar.getInstance();
            if(calendar.getTime().getTime()>verificationToken.getExpirationTime().getTime())
            {
                verificationTokenRepository.delete(verificationToken);
                return "Token expired";
            }
            User user = verificationToken.getUser();
            System.out.println("user = " + user);
            user.setEnabled(true);
            userRepository.save(user);
            verificationTokenRepository.delete(verificationToken);
            return "Verified";
        }
        return "No user found.";
    }

    @Override
    public VerificationToken generateNewVerificationToken(String oldToken) {
        Optional<VerificationToken> optionalVerificationToken = verificationTokenRepository.findByToken(oldToken);
        try{
            VerificationToken verificationToken = optionalVerificationToken.get();
            verificationToken.setToken(UUID.randomUUID().toString());
            verificationToken.resetExpirationTime();
            verificationTokenRepository.save(verificationToken);
            return verificationToken;
        }
        catch (Exception e){
            throw e;
        }
    }
}
