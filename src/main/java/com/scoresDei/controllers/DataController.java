package com.scoresDei.controllers;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import com.scoresDei.data.Player;
import com.scoresDei.data.Team;
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
        populateDB.populateDB();
        return "populated";
    }

    @GetMapping("/games")
    public String games(Model m) {
        m.addAttribute("games", gameService.getActiveGames());
        return "active_games";
    }

    @GetMapping("/team_create")
    public String createTeam(Model m) {
        m.addAttribute("gameObj", new TeamDTO());
        return "team_create";
    }

    @GetMapping("/game_create")
    public String createGame(Model m) {
        m.addAttribute("gameObj", new GameDTO());
        m.addAttribute("allTeams", teamService.getTeams());

        return "game_create";
    }

    @GetMapping("/player_create")
    public String createPlayer(Model m) {
        m.addAttribute("playerObj", new PlayerDTO());
        m.addAttribute("allTeams", teamService.getTeams());
        return "player_create";
    }

    @PostMapping("/team_create")
    public String createTeam(@ModelAttribute TeamDTO tdto, @RequestParam("team_image") MultipartFile multipartFile)
            throws IOException {

        String uploadDir = "static/images/teams/";
        Team t = new Team(tdto.getName());
        FileUploadUtil.saveFile(uploadDir, String.valueOf(t.getId()), multipartFile);
        t.setImagePath(uploadDir + t.getId());
        this.teamService.add(t);

        return "index";
    }

    @PostMapping("/player_create")
    public String createPlayer(@ModelAttribute PlayerDTO p) {
        System.out.println("Player: " + p.toString());

        this.playerService.addPlayer(p);
        return "index";
    }

    @PostMapping("/game_create")
    public String createGame(@ModelAttribute GameDTO g) {
        System.out.println("Game: " + g.toString());
        this.gameService.add(g);
        return "index";
    }

}
