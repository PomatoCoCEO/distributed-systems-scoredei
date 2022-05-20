package com.scoresDei.populate;

import com.scoresDei.data.Team;
import com.scoresDei.services.EventService;
import com.scoresDei.services.GameService;
import com.scoresDei.services.PlayerService;
import com.scoresDei.services.TeamService;
import com.scoresDei.services.UserService;
import com.scoresDei.utils.Dotenv;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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
    private Dotenv dotenv = new Dotenv("src/main/java/com/scoresDei/.env");

    private void populateTeams() {
        // Format query for preventing encoding problems
        try {
            String charset = "UTF-8";
            // Headers for a request
            String x_rapidapi_key = dotenv.get("x-rapidapi-key");// Type here your key
            // Params
            String s = "Pulp";
            String query = String.format("league=2&season=2020",
                    URLEncoder.encode(s, charset));
            HttpResponse<JsonNode> response = Unirest.get(GET_TEAM_STRING + "?" + query)
                    .header("x-rapidapi-key", x_rapidapi_key)
                    .asJson();
            System.out.println(
                    response.getBody());
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
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }

    public PopulateDB() {
    }

    public void populateDB() {
        populateTeams();
    }

}
