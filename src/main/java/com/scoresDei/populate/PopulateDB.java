package com.scoresDei.populate;

import com.scoresDei.data.Game;
import com.scoresDei.data.Player;
import com.scoresDei.data.Team;
import com.scoresDei.services.EventService;
import com.scoresDei.services.GameService;
import com.scoresDei.services.PlayerService;
import com.scoresDei.services.TeamService;
import com.scoresDei.services.UserService;
import com.scoresDei.utils.Dotenv;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.function.Consumer;
import java.util.random.RandomGenerator;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PopulateDB {
    @Autowired
    private EventService eventService;
    @Autowired
    private GameService gameService;
    @Autowired
    private PlayerService playerService;
    @Autowired
    private TeamService teamService;
    @Autowired
    private UserService userService;
    // this string is to be used in conjugation with the page number, which allows
    // 20 players to be retrieved at a time
    private static final String GET_PLAYER_STRING = "https://v3.football.api-sports.io/players";
    private static final String GET_LEAGUE_STRING = "https://v3.football.api-sports.io/leagues?season=2021&name=UEFA Champions League";
    private static final String GET_TEAM_STRING = "https://v3.football.api-sports.io/teams";

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final int NO_ACTIVE_GAMES = 10;
    private static final int NO_PAST_GAMES = 100;
    private Dotenv dotenv = new Dotenv("src/main/java/com/scoresDei/.env");

    private void populateTeams() {
        // Format query for preventing encoding problems
        try {
            String charset = "UTF-8";
            // Headers for a request
            String x_rapidapi_key = dotenv.get("x-rapidapi-key");// Type here your key
            // Params
            String query = "league=2&season=2020";
            HttpResponse<JsonNode> response = Unirest.get(GET_TEAM_STRING + "?" + query)
                    .header("x-rapidapi-key", x_rapidapi_key)
                    .asJson();
            // System.out.println(
            // response.getBody());
            var body = response.getBody().getObject();
            var resp = body.getJSONArray("response");
            for (int i = 0; i < resp.length(); i++) {
                var team_info = resp.getJSONObject(i);
                var team = team_info.getJSONObject("team");
                String name = team.getString("name");
                String photoPath = team.getString("logo");
                Team t = new Team(name, photoPath);
                teamService.add(t);
            }
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }

    private void populatePlayers() {
        // Format query for preventing encoding problems
        try {
            String charset = "UTF-8";
            // Headers for a request
            String x_rapidapi_key = dotenv.get("x-rapidapi-key");// Type here your key
            // Params
            String s = "Pulp";
            String query = "league=2&season=2020";
            int tot_pages = 2;
            var allTeamsIterable = teamService.getTeams();
            ArrayList<Team> allTeams = new ArrayList<>();
            for (var t : allTeamsIterable)
                allTeams.add(t);
            for (int i = 0; i < tot_pages; i++) {
                HttpResponse<JsonNode> response = Unirest.get(GET_PLAYER_STRING + "?" + query + "&page=" + (i + 1))
                        .header("x-rapidapi-key", x_rapidapi_key)
                        .asJson();
                var body = response.getBody().getObject();
                var resp = body.getJSONArray("response");
                if (i == 0) {
                    // adjusting the number of pages to get, in order to avoid errors
                    int paging = body.getJSONObject("paging").getInt("total");
                    tot_pages = Math.min(paging, tot_pages);
                }
                for (int k = 0; k < resp.length(); k++) {
                    try {

                        var playerInfo = resp.getJSONObject(k);
                        System.out.println("Player info: " + playerInfo);
                        System.out.println("Pois");
                        var player = playerInfo.getJSONObject("player");
                        String name = player.getString("name");
                        var stats = playerInfo.getJSONArray("statistics");
                        String position = "";
                        for (int pos_team = 0; pos_team < stats.length(); pos_team++) {
                            var games = stats.getJSONObject(pos_team).getJSONObject("games");
                            try {
                                position = games.getString("position");
                                break;
                            } catch (Exception e) {
                                position = "";
                            }
                        }
                        String birthDateString;
                        try {
                            birthDateString = (String) player.getJSONObject("birth").get("date");
                        } catch (Exception e) {
                            birthDateString = "1970-1-1";
                        }
                        Date birthDate = DATE_FORMAT.parse(birthDateString);
                        int random_pos = (int) Math.floor(Math.random() * allTeams.size());
                        Team randomTeam = allTeams.get(random_pos);
                        Player p = new Player(name, position, birthDate, randomTeam);
                        playerService.addPlayer(p);
                    } catch (ParseException pe) {
                        pe.printStackTrace();
                    }
                }
            }
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }

    private void populateGames() {
        var teams = teamService.getTeams();
        if (teams.size() < 2)
            return; // otherwise it is impossible to make valid matches
        Date d = new Date();
        Date past = new GregorianCalendar(2021, 12, 12).getTime();
        Random r = new Random();

        for (int i = 0; i < NO_ACTIVE_GAMES; i++) {
            int f = -1, g = -1;
            while (f == g) {
                f = r.nextInt(teams.size());
                g = r.nextInt(teams.size());
            }
            int goalsA = r.nextInt(7);
            int goalsB = r.nextInt(7);
            Game game = new Game("Wembley", d, goalsA, goalsB, true, false, teams.get(f), teams.get(g));
            gameService.add(game);
        }
        for (int i = 0; i < NO_PAST_GAMES; i++) {
            int f = -1, g = -1;
            while (f == g) {
                f = r.nextInt(teams.size());
                g = r.nextInt(teams.size());
            }
            int goalsA = r.nextInt(7);
            int goalsB = r.nextInt(7);
            Game game = new Game("Wembley", d, goalsA, goalsB, false, false, teams.get(f), teams.get(g));
            gameService.add(game);
        }
    }

    public PopulateDB() {
    }

    public void populateDB() {
        populateTeams();
        populatePlayers();
        populateGames();
    }

}
