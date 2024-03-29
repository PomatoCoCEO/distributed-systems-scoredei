package com.scoresDei.data;

import java.util.ArrayList;
import java.util.Date;
// import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String location;
    private Date date;
    private int goalsA;
    private int goalsB;
    private boolean isOngoing;
    private boolean isInterrupted;
    @ManyToOne
    private Team teamA;
    @ManyToOne
    private Team teamB;

    @OneToMany(mappedBy = "game")
    private List<Event> events;

    public Game(String location, Date date, int goalsA, int goalsB, boolean isOngoing, boolean isInterrupted,
            Team teamA, Team teamB) {
        this.location = location;
        this.date = date;
        this.goalsA = goalsA;
        this.goalsB = goalsB;
        this.setOngoing(isOngoing);
        this.setInterrupted(isInterrupted);
        this.teamA = teamA;
        this.teamB = teamB;
        this.events = new ArrayList<>();
    }

    public boolean isInterrupted() {
        return isInterrupted;
    }

    public void setInterrupted(boolean isInterrupted) {
        this.isInterrupted = isInterrupted;
    }

    public boolean isOngoing() {
        return isOngoing;
    }

    public void setOngoing(boolean isOngoing) {
        this.isOngoing = isOngoing;
    }

    public Game(String location, Date date, Team teamA, Team teamB) {
        this.location = location;
        this.date = date;
        this.goalsA = 0;
        this.goalsB = 0;
        this.setOngoing(false);
        this.setInterrupted(false);
        this.teamA = teamA;
        this.teamB = teamB;
        this.events = new ArrayList<>();
    }

    public Game() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public Team getTeamA() {
        return teamA;
    }

    public void setTeamA(Team teamA) {
        this.teamA = teamA;
    }

    public Team getTeamB() {
        return teamB;
    }

    public void setTeamB(Team teamB) {
        this.teamB = teamB;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    @Override
    public String toString() {
        return "Game [date=" + date + ", goalsA=" + goalsA + ", goalsB=" + goalsB + ", id=" + id
                + ", location=" + location + ", teamA=" + teamA + ", teamB=" + teamB + "]";
    }

}
