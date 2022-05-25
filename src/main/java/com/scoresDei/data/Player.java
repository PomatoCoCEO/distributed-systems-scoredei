package com.scoresDei.data;

import java.util.ArrayList;

// import java.sql.Date;

import java.util.Date;
import java.util.List;

import javax.naming.Name;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String position;
    private Date birthDate;

    @ManyToOne
    private Team team;

    @OneToMany(mappedBy = "player")
    private List<Event> events;

    private int goalsScored;

    public Player() {
    }

    public Player(String name, String position, Date birthDate, Team team, int goalsScored) {
        this.name = name;
        this.position = position;
        this.birthDate = birthDate;
        this.team = team;
        this.events = new ArrayList<>();
        this.goalsScored = goalsScored;
    }

    public Player(String name, String position, Date birthDate, Team team) {
        this.name = name;
        this.position = position;
        this.birthDate = birthDate;
        this.team = team;
        this.events = new ArrayList<>();
        this.goalsScored = 0;
    }

    public String getName() {
        return name;
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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    @Override
    public String toString() {
        return "Player ["+ name + ", position=" + position + ", team=" + team + "]";
    }

    public int getGoalsScored() {
        return goalsScored;
    }

    public void setGoalsScored(int goalsScored) {
        this.goalsScored = goalsScored;
    }

}
