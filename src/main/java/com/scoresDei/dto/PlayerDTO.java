package com.scoresDei.dto;

import java.text.SimpleDateFormat;

// import java.sql.Date;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.scoresDei.data.Player;
import com.scoresDei.data.Team;

public class PlayerDTO {

    private String name;
    private String position;
    private String birthDate;
    private int teamId;
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public PlayerDTO() {

    }

    public PlayerDTO(Player p) {
        name = p.getName();
        position = p.getPosition();
        birthDate = DATE_FORMAT.format(p.getBirthDate());
        teamId = p.getTeam().getId();
    }

    public String getName() {
        return name;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "PlayerDTO [birthDate=" + birthDate + ", name=" + name + ", position=" + position + ", teamId=" + teamId
                + "]";
    }

}
