package com.scoresDei.dto;

import java.text.SimpleDateFormat;
import java.util.Date;
// import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.scoresDei.data.Game;
import com.scoresDei.data.Team;

public class GameDTO {
    private int id;
    private String location;
    private String date;
    private int goalsA;
    private int goalsB;

    private int teamAId;
    private int teamBId;

    private static final String FORMAT_STRING = "yyyy-MM-dd'T'HH:mm";
    private static final SimpleDateFormat DATE_HOUR_FORMAT = new SimpleDateFormat(FORMAT_STRING);

    public GameDTO() {
    }

    public GameDTO(Game g) {
        this.id = g.getId();
        this.location = g.getLocation();
        this.date = DATE_HOUR_FORMAT.format(g.getDate());
        this.goalsA = g.getGoalsA();
        this.goalsB = g.getGoalsB();
        this.teamAId = g.getTeamA().getId();
        this.teamBId = g.getTeamB().getId();
    }

    public String getLocation() {
        return location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTeamBId() {
        return teamBId;
    }

    public void setTeamBId(int teamBId) {
        this.teamBId = teamBId;
    }

    public int getTeamAId() {
        return teamAId;
    }

    public void setTeamAId(int teamAId) {
        this.teamAId = teamAId;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getGoalsA() {
        return goalsA;
    }

    public void setGoalsA(int goalsA) {
        this.goalsA = goalsA;
    }

    public int getGoalsB() {
        return goalsB;
    }

    public void setGoalsB(int goalsB) {
        this.goalsB = goalsB;
    }

    @Override
    public String toString() {
        return "GameDTO [date=" + date + ", location=" + location + ", teamAId=" + teamAId + ", teamBId=" + teamBId
                + "]";
    }

}
