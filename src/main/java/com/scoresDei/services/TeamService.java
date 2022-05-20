package com.scoresDei.services;

import com.scoresDei.data.Team;
import com.scoresDei.repositories.TeamRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamService {
    @Autowired
    private TeamRepository teamRepository;

    public void add(Team t) {
        teamRepository.save(t);
    }

    public Iterable<Team> getTeams() {
        return teamRepository.findAll();
    }
}
