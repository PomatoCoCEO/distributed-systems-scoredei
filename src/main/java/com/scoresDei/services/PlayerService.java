package com.scoresDei.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

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

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            birthDate = new GregorianCalendar(1980, 12, 12).getTime();
            e.printStackTrace();
        }
        System.out.println("Team : " + teamRepository.getTeamById(pdto.getTeamId()));
        Player p = new Player(pdto.getName(), pdto.getPosition(), birthDate, teamRepository.getTeamById(pdto.getTeamId()));
        playerRepository.save(p);
    }

    public void addPlayer(Player p) {
        playerRepository.save(p);
    }

}
