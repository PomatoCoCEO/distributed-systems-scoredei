package com.scoresDei.repositories;

import com.scoresDei.data.Player;

import org.springframework.data.repository.CrudRepository;

public interface PlayerRepository extends CrudRepository<Player, Integer> {
    
}
