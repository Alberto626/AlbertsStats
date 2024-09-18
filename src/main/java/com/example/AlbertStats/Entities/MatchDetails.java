package com.example.AlbertStats.Entities;

import java.math.BigInteger;

public class MatchDetails {//This will something from View in mySQL
    private BigInteger id;
    private BigInteger matchId;
    private boolean radiantVictory;
    private int matchDuration; //this is measured in seconds, might convert for later

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
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

    @Override
    public String toString() {
        return "MatchDetails{" +
                "id=" + id +
                ", matchId=" + matchId +
                ", radiantVictory=" + radiantVictory +
                ", matchDuration=" + matchDuration +
                '}';
    }
}
