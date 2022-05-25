package com.scoresDei.data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String imagePath;
    private int nWins;
    private int nLosses;
    private int nDraws;

    @OneToMany
    private List<Game> games;

    @OneToMany(mappedBy = "team")
    private List<Player> players;

    public Team(String name, String imagePath, int nw, int nl, int nd) {
        this.name = name;
        this.imagePath = imagePath;
        this.games = new ArrayList<>();
        this.players = new ArrayList<>();
        this.nWins = nw;
        this.nLosses = nl;
        this.nDraws = nd;
    }

    public Team(String name, String imagePath) {
        this.name = name;
        this.imagePath = imagePath;
        this.games = new ArrayList<>();
        this.players = new ArrayList<>();
        this.nWins = 0;
        this.nLosses = 0;
        this.nDraws = 0;
    }

    public Team() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    @Override
    public String toString() {
        return "Team [games=" + games + ", id=" + id + ", imagePath=" + imagePath + ", name=" + name + ", players="
                + players + "]";
    }

    public int getnWins() {
        return nWins;
    }

    public void setnWins(int nWins) {
        this.nWins = nWins;
    }

    public int getnLosses() {
        return nLosses;
    }

    public void setnLosses(int nLosses) {
        this.nLosses = nLosses;
    }

    public int getnDraws() {
        return nDraws;
    }

    public void setnDraws(int nDraws) {
        this.nDraws = nDraws;
    }

}
