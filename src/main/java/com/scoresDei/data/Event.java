package com.scoresDei.data;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Event {
    public enum EventType {
        START, END, GOAL, OWN_GOAL, YELLOW_CARD, RED_CARD, INTERRUPT, RESUME
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private EventType type;
    private Date timeStamp;
    @ManyToOne
    private User user;
    @ManyToOne
    private Game game;
    @ManyToOne
    private Player player;

    public Event() {
    }

    public Event(EventType type, Date timeStamp, User user, Game game, Player player) {
        this.type = type;
        this.timeStamp = timeStamp;
        this.user = user;
        this.game = game;
        this.player = player;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public String toString() {
        return "Event [game=" + game + ", player=" + player + ", timeStamp=" + timeStamp + ", type=" + type + ", user="
                + user + "]";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
