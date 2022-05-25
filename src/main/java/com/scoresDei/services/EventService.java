package com.scoresDei.services;

import com.scoresDei.data.Event;
import com.scoresDei.repositories.EventRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    public void addEvent(Event e) {
        eventRepository.save(e);
    }
}
