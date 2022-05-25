package com.scoresDei.services;

import java.util.ArrayList;

import com.scoresDei.data.Game;
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
        Team teamA = teamRepository.getTeamById(gdto.getTeamAId());
        Team teamB = teamRepository.getTeamById(gdto.getTeamBId());
        Game g = new Game(gdto.getLocation(), gdto.getDate(), teamA, teamB);
        gameRepository.save(g);
    }
}
