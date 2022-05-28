package com.scoresDei.controllers;

import java.security.Principal;

import com.scoresDei.data.Game;
import com.scoresDei.data.User;
import com.scoresDei.dto.EventDTO;
import com.scoresDei.services.EventService;
import com.scoresDei.services.GameService;
import com.scoresDei.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    GameService gameService;

    @Autowired
    EventService eventService;

    @Autowired
    UserService userService;

    @GetMapping("/event_register")
    public String eventRegister(Model m, @RequestParam("gameid") int gameId, Principal principal) {
        if (principal != null) {
            User u = (User) userService.loadUserByUsername(principal.getName());
            m.addAttribute("user", u);
        }
        Game g = gameService.getGameById(gameId);
        var evdto = new EventDTO();
        evdto.setGameId(gameId);
        m.addAttribute("event", evdto);
        m.addAttribute("game", g);
        return "event_register";
    }

    @PostMapping("/event_register")
    public String registerEvent(@ModelAttribute EventDTO e, Model m, Principal principal) {
        if (principal != null) {
            User u = (User) userService.loadUserByUsername(principal.getName());
            m.addAttribute("user", u);
        }
        System.out.println("Event: " + e.toString());
        this.eventService.addEventFromDTO(e);
        return "redirect:/index";
    }
}
