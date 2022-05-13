package com.scoresDei.services;

import com.scoresDei.data.User;
import com.scoresDei.dto.UserDTO;
import com.scoresDei.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private boolean checkEmail(String email) {
        return false;
    }

    public void addUser(UserDTO u) {

        System.out.println(u);

        if (u.getAdminCode() != null && !u.getAdminCode().equals("admin")) {
            return;
        }

        if (userRepository.existsUserByEmail(u.getEmail())) {
            // erro

            return;
        }

        if (userRepository.existsUserByUsername(u.getUsername())) {
            // erro
            return;
        }
        if (userRepository.existsUserByTelephone(u.getTelephone())) {
            // erro
            return;
        }

        // hash pw
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        // check duplicate
        String pwHashed = (passwordEncoder.encode(u.getPassword()));
        System.out.println("Hashed password: " + pwHashed);
        User newUser = new User(u.getUsername(), pwHashed, u.getTelephone(), u.getEmail(), u.getAdminCode());

        userRepository.save(newUser);
    }

}
