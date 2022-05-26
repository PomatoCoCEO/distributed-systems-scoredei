package com.scoresDei.services;

import java.util.Date;

import com.scoresDei.data.Event;
import com.scoresDei.data.Game;
import com.scoresDei.data.Player;
import com.scoresDei.data.Event.EventType;
import com.scoresDei.dto.EventDTO;
import com.scoresDei.repositories.EventRepository;
import com.scoresDei.repositories.GameRepository;
import com.scoresDei.repositories.PlayerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private PlayerRepository playerRepository;

    public void addEvent(Event e) {
        eventRepository.save(e);
    }

    public void addEventFromDTO(EventDTO eventDto) {
        EventType aidType;
        Player p = null;
        switch (eventDto.getType()) {
            case 0:
                aidType = EventType.START;
                break;
            case 1:
                aidType = EventType.END;
                break;
            case 2:
                aidType = EventType.GOAL;
                p = playerRepository.findById(eventDto.getPlayerId()).get();
                break;
            case 3:
                aidType = EventType.YELLOW_CARD;
                p = playerRepository.findById(eventDto.getPlayerId()).get();
                break;
            case 4:
                aidType = EventType.RED_CARD;
                p = playerRepository.findById(eventDto.getPlayerId()).get();
                break;
            case 5:
                aidType = EventType.INTERRUPT;
                break;
            default:
                aidType = EventType.RESUME;
                break;
        }

        Game g = gameRepository.findById(eventDto.getGameId()).get();
        // TODO: here we need to perform the necessary checks
        // if the game is not started, we should not be able to add events
        // if the game is ended, we should not be able to add events
        if (!g.isOngoing() && (aidType != EventType.START || g.getEvents().size() > 0)) { // we need to throw an
                                                                                          // exception
            return;
        }
        switch (aidType) {
            case START:
                g.setOngoing(true);
                g.setDate(new Date());
                gameRepository.save(g);
                break;
            case END:
                g.setOngoing(false);
                gameRepository.save(g);
                break;
            default:
                System.out.println("Not supported yet");
                break;
        }
        Event e = new Event(aidType, new Date(), null, g, p);
        eventRepository.save(e);
    }
}
