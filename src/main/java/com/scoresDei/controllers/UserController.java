package com.scoresDei.controllers;

import com.scoresDei.data.Game;
import com.scoresDei.dto.EventDTO;
import com.scoresDei.services.EventService;
import com.scoresDei.services.GameService;

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

    @GetMapping("/event_register")
    public String eventRegister(Model m, @RequestParam("gameid") int gameId) {
        Game g = gameService.getGameById(gameId);
        var evdto = new EventDTO();
        evdto.setGameId(gameId);
        m.addAttribute("event", evdto);
        m.addAttribute("game", g);
        return "event_register";
    }

    @PostMapping("/event_register")
    public String registerEvent(@ModelAttribute EventDTO e) {
        System.out.println("Event: " + e.toString());
        this.eventService.addEventFromDTO(e);
        return "redirect:/index";
    }
}
