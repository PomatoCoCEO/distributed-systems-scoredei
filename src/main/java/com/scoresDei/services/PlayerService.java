package com.scoresDei.services;

import com.scoresDei.data.Player;
import com.scoresDei.dto.PlayerDTO;
import com.scoresDei.repositories.PlayerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {
    @Autowired
    private PlayerRepository playerRepository;

    public void addPlayer(PlayerDTO pdto) {
        Player p = new Player(pdto.getName(), pdto.getPosition(), pdto.getBirthDate(), pdto.getTeam());
        playerRepository.save(p);
    }

    public void addPlayer(Player p) {
        playerRepository.save(p);
    }
    
}
