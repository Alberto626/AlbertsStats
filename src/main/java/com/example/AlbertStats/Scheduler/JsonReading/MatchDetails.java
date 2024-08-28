package com.example.AlbertStats.Scheduler.JsonReading;

import java.math.BigInteger;
import java.util.List;

public class MatchDetails {
    private int id;
    private BigInteger matchId; //TODO check if needs to be changed to long or BigLong
    private boolean radiantVictory;
    private int matchDuration; //this is measured in seconds, might convert for later
    private List<PlayerDetails> playerDetails;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigInteger getMatchId() {
        return matchId;
    }

    public void setMatchId(BigInteger matchId) {
        this.matchId = matchId;
    }

    public boolean isRadiantVictory() {
        return radiantVictory;
    }

    public void setRadiantVictory(boolean radiantVictory) {
        this.radiantVictory = radiantVictory;
    }

    public int getMatchDuration() {
        return matchDuration;
    }

    public void setMatchDuration(int matchDuration) {
        this.matchDuration = matchDuration;
    }

    public List<PlayerDetails> getPlayerDetails() {
        return playerDetails;
    }

    public void setPlayerDetails(List<PlayerDetails> playerDetails) {
        this.playerDetails = playerDetails;
    }
}
