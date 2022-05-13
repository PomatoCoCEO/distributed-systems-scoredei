package com.scoresDei.controllers;

import com.scoresDei.data.User;
import com.scoresDei.dto.UserDTO;
import com.scoresDei.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class DataController {
    @Autowired
    UserService userService;

    @GetMapping({ "/", "/index" })
    public String indexx() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        System.out.println("Got a register request");
        return "user_register";
    }

    @PostMapping("/register")
    public String createUser(@ModelAttribute UserDTO u) {
        System.out.println("User: "+u.toString());
        this.userService.addUser(u);
        return "index";
    }
}
