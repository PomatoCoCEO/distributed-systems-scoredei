package com.scoresDei.controllers;

import com.scoresDei.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

public class DataController {
    @Autowired
    UserService userService;

    @GetMapping("/")
    public String index() {
        return "index";
    }
}
