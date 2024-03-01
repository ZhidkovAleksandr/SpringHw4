package com.rep.simpProd.services;

import com.rep.simpProd.entity.User;
import com.rep.simpProd.interfaces.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void registerUser(User user) {
        //PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setEnabled(true);
        String cryptedPass = passwordEncoder.encode(user.getPassword());
        user.setPassword(cryptedPass);
        userRepository.save(user);
    }

    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }

}
