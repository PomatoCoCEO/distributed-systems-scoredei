package com.scoresDei.controllers;

import com.scoresDei.data.Team;
import com.scoresDei.data.User;
import com.scoresDei.dto.UserDTO;
import com.scoresDei.populate.PopulateDB;
import com.scoresDei.services.EventService;
import com.scoresDei.services.GameService;
import com.scoresDei.services.PlayerService;
import com.scoresDei.services.TeamService;
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
    EventService eventService;
    @Autowired
    GameService gameService;
    @Autowired
    PlayerService playerService;
    @Autowired
    TeamService teamService;
    @Autowired
    UserService userService;
    @Autowired
    PopulateDB populateDB;

    @GetMapping({ "/", "/index" })
    public String index() {
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
        System.out.println("User: " + u.toString());
        this.userService.addUser(u);
        return "index";
    }

    @GetMapping("/populate")
    public String populate() {
        Team team = new Team("Benfica", "path-to-image");
        teamService.add(team);
        populateDB.populateDB();
        return "populated";
    }

    @GetMapping("/games")
    public String games(Model m) {
        m.addAttribute("games", gameService.getActiveGames());
        return "active_games";
    }
}
