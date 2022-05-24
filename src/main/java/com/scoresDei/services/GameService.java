package com.scoresDei.services;

import java.util.ArrayList;

import com.scoresDei.data.Game;
import com.scoresDei.repositories.GameRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {
    @Autowired
    private GameRepository gameRepository;

    public ArrayList<Game> getActiveGames() {
        return gameRepository.getActiveGames();
    }
}
