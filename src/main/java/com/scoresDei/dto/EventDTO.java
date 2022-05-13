package com.scoresDei.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

public class EventDTO {

    private int type;
    private Date timeStamp;

    private int gameId;

    private String playerName;

    public String getPlayerName() {
        return playerName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }


    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

}
