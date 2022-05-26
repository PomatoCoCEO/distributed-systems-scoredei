package com.scoresDei.services;

import java.util.ArrayList;

import com.scoresDei.data.User;
import com.scoresDei.dto.UserDTO;
import com.scoresDei.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    public ArrayList<User> getAllUsers() {
        var users = userRepository.findAll();
        ArrayList<User> ans = new ArrayList<User>();
        for (var u : users)
            ans.add(u);
        return ans;
    }

    public void addUser(User u) {
        userRepository.save(u);
    }

    public void addUsers(Iterable<User> users) {
        userRepository.saveAll(users);
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        return user;
    }

}
