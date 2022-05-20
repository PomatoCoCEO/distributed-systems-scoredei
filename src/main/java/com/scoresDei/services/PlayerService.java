package com.scoresDei.services;

import com.scoresDei.data.Player;
import com.scoresDei.repositories.PlayerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {
    @Autowired
    private PlayerRepository playerRepository;

    public void addPlayer(Player p) {
        playerRepository.save(p);
    }
}
