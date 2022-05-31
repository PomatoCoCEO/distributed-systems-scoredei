package com.scoresDei.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.ArrayList;

import com.scoresDei.data.Player;
import com.scoresDei.dto.PlayerDTO;
import com.scoresDei.repositories.PlayerRepository;
import com.scoresDei.repositories.TeamRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {
    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private TeamRepository teamRepository;

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public void addPlayer(PlayerDTO pdto) {
        Date birthDate;
        try {
            birthDate = DATE_FORMAT.parse(pdto.getBirthDate());
            if (birthDate.after(new Date())) {
                throw new IllegalArgumentException("Birth date can't be in the future");
            }
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            birthDate = new GregorianCalendar(1980, 12, 12).getTime();
            e.printStackTrace();
        }
        System.out.println("Team : " + teamRepository.getTeamById(pdto.getTeamId()));
        Player p = new Player(pdto.getName(), pdto.getPosition(), birthDate,
                teamRepository.getTeamById(pdto.getTeamId()));
        playerRepository.save(p);
    }

    public void addPlayer(Player p) {
        playerRepository.save(p);
    }

    public void updatePlayer(PlayerDTO pdto, int id) {
        Date birthDate;
        Player p = getPlayerById(id);
        try {
            birthDate = DATE_FORMAT.parse(pdto.getBirthDate());
            if (birthDate.after(new Date()))
                throw new IllegalArgumentException("Birth date can't be in the future");
        } catch (ParseException e) {

            birthDate = new GregorianCalendar(1980, 12, 12).getTime();
            e.printStackTrace();
        }
        p.setName(pdto.getName());
        p.setPosition(pdto.getPosition());
        p.setBirthDate(birthDate);
        p.setTeam(teamRepository.getTeamById(pdto.getTeamId()));
        playerRepository.save(p);
    }

    public void updatePlayer(Player p) {

        playerRepository.save(p);
    }

    public ArrayList<Player> getPlayers() {
        ArrayList<Player> ans = new ArrayList<>();
        for (var p : playerRepository.findAll())
            ans.add(p);
        return ans;
    }

    public int loadGoals(Player p) {
        return playerRepository.loadGoals(p);
    }

    public Player getPlayerById(int id) {
        return playerRepository.findById(id).get();
    }

    public Player getBestScorer() {
        Player bestScorer = playerRepository.bestScorer();
        return bestScorer;
    }

}
