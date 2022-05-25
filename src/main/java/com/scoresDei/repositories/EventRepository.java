package com.scoresDei.repositories;

import com.scoresDei.data.Event;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EventRepository extends CrudRepository<Event, Integer> {

}
