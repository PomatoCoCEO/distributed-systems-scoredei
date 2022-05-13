package com.scoresDei.dto;

import java.util.Date;
// import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

public class GameDTO {
    private int id;
    private String location;
    private Date date;
    private int goalsA;
    private int goalsB;
    private String teamA;
    private String teamB;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public String getTeamA() {
        return teamA;
    }

    public void setTeamA(String teamA) {
        this.teamA = teamA;
    }

    public String getTeamB() {
        return teamB;
    }

    public void setTeamB(String teamB) {
        this.teamB = teamB;
    }

}
