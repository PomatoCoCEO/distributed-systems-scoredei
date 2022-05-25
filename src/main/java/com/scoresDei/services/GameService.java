package com.scoresDei.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import com.scoresDei.data.Game;
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

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public ArrayList<Game> getActiveGames() {
        ArrayList<Game> ans = new ArrayList<>();
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

    public void add(Game g) {
        gameRepository.save(g);
    }

    public void add(GameDTO gdto) {
        Date date;
        try {
            date = DATE_FORMAT.parse(gdto.getDate());

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            date = new GregorianCalendar(1980, 12, 12).getTime();
            e.printStackTrace();
        }
        Game g = new Game(gdto.getLocation(), date, teamRepository.getTeamById(gdto.getTeamAId()),
                teamRepository.getTeamById(gdto.getTeamBId()));
        gameRepository.save(g);
    }
}
