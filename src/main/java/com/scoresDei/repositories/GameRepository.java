package com.scoresDei.repositories;

import com.scoresDei.data.Game;

import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository<Game, Integer> {

}
