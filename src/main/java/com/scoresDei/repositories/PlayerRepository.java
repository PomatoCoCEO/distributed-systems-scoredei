package com.scoresDei.repositories;

import com.scoresDei.data.Player;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Integer> {

    // 2 means goal
    @Query("select count(*) from Event e where e.type = 2 and e.player = ?1")
    public int loadGoals(Player p);

    @Query("select p from Player p where p.goalsScored = ( select max(pl.goalsScored) from Player pl)")
    public Player bestScorer();

}
