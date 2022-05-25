package com.scoresDei.repositories;

import com.scoresDei.data.Game;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository<Game, Integer> {

    @Query("select g from Game g where g.isOngoing=true")
    public Iterable<Game> getActiveGames();

    @Query("select g from Game g where g.isOngoing=false and g.date < CURRENT_TIMESTAMP")
    public Iterable<Game> getPastGames();

    @Query("select g from Game g where g.isOngoing=false and g.date > CURRENT_TIMESTAMP")
    public Iterable<Game> getFutureGames();

}
