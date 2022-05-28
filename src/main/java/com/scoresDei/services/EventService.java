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
import com.scoresDei.repositories.TeamRepository;

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

    @Autowired
    private TeamRepository teamRepository;

    public void addEvent(Event e) {
        eventRepository.save(e);
    }

    public Event getEventById(int id) {
        return eventRepository.findById(id).get();
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
                aidType = EventType.OWN_GOAL;
                p = playerRepository.findById(eventDto.getPlayerId()).get();
                break;
            case 4:
                aidType = EventType.YELLOW_CARD;
                p = playerRepository.findById(eventDto.getPlayerId()).get();
                break;
            case 5:
                aidType = EventType.RED_CARD;
                p = playerRepository.findById(eventDto.getPlayerId()).get();
                break;
            case 6:
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
        if (g.isInterrupted() && aidType != EventType.RESUME) {
            return;
        }
        if (g.isOngoing() && aidType == EventType.START) {
            return;
        }
        if (aidType == EventType.RESUME && !g.isInterrupted()) {
            // TODO: throw exception: player already expelled
            return;
        }
        if (p != null && (gameRepository.yellowCards(g, p) >= 2 || gameRepository.redCards(g, p) >= 1)) {
            // TODO: throw exception: player already expelled
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
                if (g.getGoalsA() > g.getGoalsB()) {
                    g.getTeamA().setnWins(g.getTeamA().getnWins() + 1);
                    g.getTeamB().setnLosses(g.getTeamB().getnLosses() + 1);
                } else if (g.getGoalsA() < g.getGoalsB()) {
                    g.getTeamB().setnWins(g.getTeamB().getnWins() + 1);
                    g.getTeamA().setnLosses(g.getTeamA().getnLosses() + 1);
                } else {
                    g.getTeamA().setnDraws(g.getTeamA().getnDraws() + 1);
                    g.getTeamB().setnDraws(g.getTeamB().getnDraws() + 1);
                }
                teamRepository.save(g.getTeamA());
                teamRepository.save(g.getTeamB());
                gameRepository.save(g);
                break;
            case GOAL:
                if (p.getTeam().getId() == g.getTeamA().getId()) {
                    g.setGoalsA(g.getGoalsA() + 1);
                } else {
                    g.setGoalsB(g.getGoalsB() + 1);
                }
                p.setGoalsScored(p.getGoalsScored() + 1);
                playerRepository.save(p); // also saving the goals scored
                gameRepository.save(g);
                break;
            case OWN_GOAL:
                if (p.getTeam().getId() == g.getTeamA().getId()) {
                    g.setGoalsB(g.getGoalsB() + 1);
                } else {
                    g.setGoalsA(g.getGoalsA() + 1);
                }
                gameRepository.save(g);
                break;
            case YELLOW_CARD:

                break;
            case RED_CARD:

                break;
            case INTERRUPT:
                g.setInterrupted(true);
                gameRepository.save(g);
                break;
            case RESUME:
                g.setInterrupted(false);
                gameRepository.save(g);
                break;
        }
        Event e = new Event(aidType, new Date(), null, g, p);
        System.out.println("Saving new event: " + e);
        eventRepository.save(e);
    }
}
