package com.scoresDei.repositories;

import com.scoresDei.data.Game;
import com.scoresDei.data.Player;
import com.scoresDei.data.Team;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends CrudRepository<Game, Integer> {

    @Query("select g from Game g where g.isOngoing=true")
    public Iterable<Game> getActiveGames();

    @Query("select g from Game g where g.isOngoing=false and g.date < CURRENT_TIMESTAMP")
    public Iterable<Game> getPastGames();

    @Query("select g from Game g where g.isOngoing=false and g.date > CURRENT_TIMESTAMP")
    public Iterable<Game> getFutureGames();

    @Query("select count(*) from Game g, Player p, Event e where e.game=?1 and e.game = g and e.player=p and e.player=?2 and e.type=5")
    public int redCards(Game g, Player p);

    @Query("select count(*) from Game g, Player p, Event e where e.game=?1 and e.game = g and e.player=p and e.player=?2 and e.type=4")
    public int yellowCards(Game g, Player p);

    
}
