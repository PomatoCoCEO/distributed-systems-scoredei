package com.scoresDei.services;

import java.util.ArrayList;

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

    public Team findById(int id) {
        var ans = teamRepository.findById(id);
        return ans.get();
    }

    public void updateTeam(Team t) {
        teamRepository.save(t);
    }

    public ArrayList<Team> getTeams() {
        ArrayList<Team> ans = new ArrayList<>();
        for (var t : teamRepository.findAll())
            ans.add(t);
        return ans;
    }

    public int getTotalGames(Team t) {
        return t.getnDraws() + t.getnWins() + t.getnLosses();
    }
}
