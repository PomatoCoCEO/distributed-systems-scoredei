package com.scoresDei.controllers;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import com.scoresDei.data.Game;
import com.scoresDei.data.Player;
import com.scoresDei.data.Team;
import com.scoresDei.data.User;
import com.scoresDei.dto.EventDTO;
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
import com.scoresDei.utils.FileUploadUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
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

    @GetMapping({ "/", "/index" })
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }





    @GetMapping("/game_details")
    public String gameDetails(@RequestParam(name = "id", required = true) int id, Model m) {
        m.addAttribute("game", gameService.getGameById(id));
        return "game_details";

    }

    @GetMapping("/games")
    public String games(Model m) {
        m.addAttribute("games", gameService.getActiveGames());
        return "active_games";
    }

    @GetMapping("/future_games")
    public String futureGames(Model m) {
        m.addAttribute("games", gameService.getFutureGames());
        return "future_games";
    }

    @GetMapping("/past_games")
    public String pastGames(Model m) {
        m.addAttribute("games", gameService.getFutureGames());
        return "past_games";
    }

    @GetMapping("/team_details")
    public String teamDetails(@RequestParam(name = "id", required = true) int id, Model m) {
        m.addAttribute("team", teamService.findById(id));
        return "team_details";
    }

     @GetMapping("/player_details")
    public String playerDetails(@RequestParam(name = "id", required = true) int id, Model m) {
        m.addAttribute("player", playerService.getPlayerById(id));
        return "player_details";
    }

    @GetMapping("/teams")
    public String teams(Model m) {
        m.addAttribute("teams", teamService.getSortedTeams());
        return "teams";
    }

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
        return "redirect:index";
    }

}
