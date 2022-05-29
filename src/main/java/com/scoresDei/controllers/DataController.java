package com.scoresDei.controllers;

import java.io.IOException;
import java.security.Principal;
import java.sql.Date;
import java.text.SimpleDateFormat;

import com.scoresDei.data.Game;
import com.scoresDei.data.Player;
import com.scoresDei.data.Team;
import com.scoresDei.data.User;
import com.scoresDei.dto.EventDTO;
import com.scoresDei.dto.GameDTO;
import com.scoresDei.dto.PlayerDTO;
import com.scoresDei.dto.PopulateDTO;
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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    @Autowired
    PopulateDB populateDB;

    @GetMapping({ "/", "/index" })
    public String index(Principal principal, Model m) {
        if (principal != null) {
            User u = (User) userService.loadUserByUsername(principal.getName());
            m.addAttribute("user", u);
        }
        return "index";
    }

    @GetMapping("/error")
    public String error() {

        return "error";
    }

    @GetMapping("/populate")
    public String populate(Principal principal, Model m) {
        if (principal != null) {
            User u = (User) userService.loadUserByUsername(principal.getName());
            m.addAttribute("user", u);
        }

        m.addAttribute("populate", new PopulateDTO());

        return "populate";
    }

    @PostMapping("/populate")
    public String populate(@ModelAttribute PopulateDTO pop, Principal principal,
            Model m) {
        if (principal != null) {
            User u = (User) userService.loadUserByUsername(principal.getName());
            m.addAttribute("user", u);
        }
        System.out.println("code " + pop.getCode());
        if (pop.getCode().equals("admin")) {
            populateDB.populateDB();
            return "index";
        }
        return "redirect:/populate";

    }

    @GetMapping("/game_details")
    public String gameDetails(@RequestParam(name = "id", required = true) int id, Model m, Principal principal) {
        if (principal != null) {
            User u = (User) userService.loadUserByUsername(principal.getName());
            m.addAttribute("user", u);
        }
        m.addAttribute("game", gameService.getGameById(id));
        return "game_details";

    }

    @GetMapping("/games")
    public String games(Model m, Principal principal) {
        if (principal != null) {
            User u = (User) userService.loadUserByUsername(principal.getName());
            m.addAttribute("user", u);
        }
        m.addAttribute("games", gameService.getActiveGames());
        return "active_games";
    }

    @GetMapping("/future_games")
    public String futureGames(Model m, Principal principal) {
        if (principal != null) {
            User u = (User) userService.loadUserByUsername(principal.getName());
            m.addAttribute("user", u);
        }
        m.addAttribute("games", gameService.getFutureGames());
        // System.out.println(m.getAttribute("games"));
        return "future_games";
    }

    @GetMapping("/past_games")
    public String pastGames(Model m, Principal principal) {
        if (principal != null) {
            User u = (User) userService.loadUserByUsername(principal.getName());
            m.addAttribute("user", u);
        }
        m.addAttribute("games", gameService.getPastGames());
        return "past_games";
    }

    @GetMapping("/team_details")
    public String teamDetails(@RequestParam(name = "id", required = true) int id, Model m, Principal principal) {
        if (principal != null) {
            User u = (User) userService.loadUserByUsername(principal.getName());
            m.addAttribute("user", u);
        }
        m.addAttribute("team", teamService.findById(id));
        return "team_details";
    }

    @GetMapping("/player_details")
    public String playerDetails(@RequestParam(name = "id", required = true) int id, Model m, Principal principal) {
        if (principal != null) {
            User u = (User) userService.loadUserByUsername(principal.getName());
            m.addAttribute("user", u);
        }
        var p = playerService.getPlayerById(id);
        m.addAttribute("player", p);

        return "player_details";
    }

    @GetMapping("/best_scorer")
    public String BestScorer(Model m, Principal principal) {
        if (principal != null) {
            User u = (User) userService.loadUserByUsername(principal.getName());
            m.addAttribute("user", u);
        }
        var p = playerService.getBestScorer();
        m.addAttribute("player", p);
        return "player_details";
    }

    @GetMapping("/teams")
    public String teams(Model m, Principal principal) {
        if (principal != null) {
            User u = (User) userService.loadUserByUsername(principal.getName());
            m.addAttribute("user", u);
        }
        m.addAttribute("teams", teamService.getSortedTeams());
        return "teams";
    }

}
