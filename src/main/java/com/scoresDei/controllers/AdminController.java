package com.scoresDei.controllers;

import java.io.IOException;
import java.security.Principal;

import com.scoresDei.data.User;
import com.scoresDei.dto.GameDTO;
import com.scoresDei.dto.PlayerDTO;
import com.scoresDei.dto.TeamDTO;
import com.scoresDei.dto.UserDTO;
import com.scoresDei.populate.PopulateDB;
import com.scoresDei.services.EventService;
import com.scoresDei.services.GameService;
import com.scoresDei.services.PlayerService;
import com.scoresDei.services.TeamService;
import com.scoresDei.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/admin")
public class AdminController {
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

    @GetMapping("/register")
    public String register(Model m, Principal principal) {
        if (principal != null) {
            User u = (User) userService.loadUserByUsername(principal.getName());
            m.addAttribute("user", u);
        }
        return "user_register";
    }

    @PostMapping("/register")
    public String createUser(@ModelAttribute UserDTO u, Model m, Principal principal) {
        if (principal != null) {
            User user = (User) userService.loadUserByUsername(principal.getName());
            m.addAttribute("user", user);
        }
        System.out.println("User: " + u.toString());
        this.userService.addUser(u);
        return "index";
    }

    @GetMapping("/populate")
    public String populate() {
        populateDB.populateDB();
        return "populated";
    }

    @GetMapping("/team/create")
    public String createTeam(Model m, Principal principal) {
        if (principal != null) {
            User u = (User) userService.loadUserByUsername(principal.getName());
            m.addAttribute("user", u);
        }
        m.addAttribute("team", new TeamDTO());
        return "team_create";
    }

    @GetMapping("/game/create")
    public String createGame(Model m, Principal principal) {
        if (principal != null) {
            User u = (User) userService.loadUserByUsername(principal.getName());
            m.addAttribute("user", u);
        }
        m.addAttribute("gameObj", new GameDTO());
        m.addAttribute("allTeams", teamService.getTeams());

        return "game_create";
    }

    @GetMapping("/player/create")
    public String createPlayer(Model m, Principal principal) {
        if (principal != null) {
            User u = (User) userService.loadUserByUsername(principal.getName());
            m.addAttribute("user", u);
        }
        m.addAttribute("playerObj", new PlayerDTO());
        m.addAttribute("allTeams", teamService.getTeams());
        return "player_create";
    }

    @PostMapping("/team/create")
    public String createTeam(@ModelAttribute TeamDTO t, @RequestParam("team_image") MultipartFile multipartFile,
            Model m, Principal principal) throws IOException {
        if (principal != null) {
            User u = (User) userService.loadUserByUsername(principal.getName());
            m.addAttribute("user", u);
        }
        this.teamService.addTeamWithPhoto(t, multipartFile);
        return "redirect:/teams";
    }

    @PostMapping("/player/create")
    public String createPlayer(@ModelAttribute PlayerDTO p, Model m, Principal principal) {
        System.out.println("Player: " + p.toString());
        if (principal != null) {
            User u = (User) userService.loadUserByUsername(principal.getName());
            m.addAttribute("user", u);
        }
        this.playerService.addPlayer(p);
        return "redirect:/index";
    }

    @PostMapping("/game/create")
    public String createGame(@ModelAttribute GameDTO g, Model m, Principal principal) {
        if (principal != null) {
            User u = (User) userService.loadUserByUsername(principal.getName());
            m.addAttribute("user", u);
        }
        System.out.println("Game: " + g.toString());
        this.gameService.add(g);
        System.out.println("added " + g);
        return "redirect:/future_games";
    }
}
