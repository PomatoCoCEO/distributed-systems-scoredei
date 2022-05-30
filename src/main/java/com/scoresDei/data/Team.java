package com.scoresDei.data;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class Team implements Comparable<Team> {
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

    @Transient
    public static final Comparator<Team> BY_WINS = Comparator.comparing(Team::getnWins).reversed();
    @Transient
    public static final Comparator<Team> BY_LOSSES = Comparator.comparing(Team::getnLosses).reversed();
    @Transient
    public static final Comparator<Team> BY_DRAWS = Comparator.comparing(Team::getnDraws).reversed();

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

    public Team(String name) {
        this.name = name;
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
        return "Team [" + name + "]";
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

    

    @Override
    public int compareTo(Team t) {
        if ((this.nWins * 3 + this.nDraws) > (t.nWins * 3 + t.nDraws))
            return -1;
        else if ((this.nWins * 3 + this.nDraws) < (t.nWins * 3 + t.nDraws))
            return 1;
        else
            return 0;
    }

}
