package com.scoresDei.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import com.scoresDei.data.Game;
import com.scoresDei.data.Player;
import com.scoresDei.data.Team;
import com.scoresDei.dto.GameDTO;
import com.scoresDei.repositories.GameRepository;
import com.scoresDei.repositories.TeamRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {
    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private TeamRepository teamRepository;
    private static final String FORMAT_STRING = "yyyy-MM-dd HH:mm:ss";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(FORMAT_STRING);

    public ArrayList<Game> getActiveGames() {
        ArrayList<Game> ans = new ArrayList<>();
        for (var i : gameRepository.getActiveGames()) {
            ans.add(i);
        }
        return ans;
    }

    public ArrayList<Game> getPastGames() {
        ArrayList<Game> ans = new ArrayList<>();
        Date date;
        try {
            date = DATE_FORMAT.parse(DATE_FORMAT.format(new Date()));
        } catch (ParseException e) {
            date = new GregorianCalendar(1980, 12, 12).getTime();
            e.printStackTrace();

        }
        // System.out.println("here" + gameRepository.getPastGames(date));
        for (var i : gameRepository.getPastGames(date)) {
            ans.add(i);
        }
        return ans;
    }

    public ArrayList<Game> getFutureGames() {
        ArrayList<Game> ans = new ArrayList<>();
        Date date;
        try {
            date = DATE_FORMAT.parse(DATE_FORMAT.format(new Date()));

        } catch (ParseException e) {
            date = new GregorianCalendar(1980, 12, 12).getTime();
            e.printStackTrace();

        }
        // System.out.println("here" + gameRepository.getFutureGames(date));
        for (var i : gameRepository.getFutureGames(date)) {
            ans.add(i);
        }
        return ans;
    }

    public ArrayList<Game> getActiveAndFutureGames() {
        ArrayList<Game> ans = new ArrayList<>();
        Date date;
        try {
            date = DATE_FORMAT.parse(DATE_FORMAT.format(new Date()));

        } catch (ParseException e) {
            date = new GregorianCalendar(1980, 12, 12).getTime();
            e.printStackTrace();

        }
        for (var i : gameRepository.getFutureGames(date)) {
            ans.add(i);
        }
        for (var i : gameRepository.getActiveGames()) {
            ans.add(i);
        }
        return ans;
    }

    public ArrayList<Game> getAllGames() {
        ArrayList<Game> ans = new ArrayList<>();
        for (var i : gameRepository.findAll()) {
            ans.add(i);
        }
        return ans;
    }

    public Game getGameById(int id) {
        return gameRepository.findById(id).get();
    }

    public void add(Game g) {
        gameRepository.save(g);
    }

    public void add(GameDTO gdto) {
        Date date;
        try {
            String s = LocalDateTime.parse(gdto.getDate(), DateTimeFormatter.ISO_DATE_TIME)
                    .format(DateTimeFormatter.ofPattern(FORMAT_STRING));
            date = DATE_FORMAT.parse(s);

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            date = new GregorianCalendar(1980, 12, 12).getTime();
            e.printStackTrace();
        }
        if (gdto.getTeamAId() == gdto.getTeamBId()) {
            // TODO: return some kind of error
            return;
        }
        Game g = new Game(gdto.getLocation(), date, teamRepository.getTeamById(gdto.getTeamAId()),
                teamRepository.getTeamById(gdto.getTeamBId()));
        gameRepository.save(g);
    }

    public boolean isExpelled(Game g, Player p) {
        return gameRepository.redCards(g, p) >= 1 || gameRepository.yellowCards(g, p) > 2;
    }
}
