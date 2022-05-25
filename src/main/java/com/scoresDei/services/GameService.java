package com.scoresDei.services;

import java.util.ArrayList;

import com.scoresDei.data.Game;
import com.scoresDei.dto.GameDTO;
import com.scoresDei.repositories.GameRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {
    @Autowired
    private GameRepository gameRepository;

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
        Game g = new Game(gdto.getLocation(), gdto.getDate(), gdto.getTeamA(), gdto.getTeamB());
        gameRepository.save(g);
    }
}
