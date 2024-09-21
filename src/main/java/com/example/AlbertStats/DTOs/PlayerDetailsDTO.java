package com.example.AlbertStats.DTOs;

import com.example.AlbertStats.Entities.Hero;
import com.example.AlbertStats.Entities.Item;
import com.example.AlbertStats.Entities.MatchDetails;

import java.math.BigInteger;
import java.util.List;

public class PlayerDetailsDTO {//This will be like playerMatchDetails but all the info
    private BigInteger playerId;
    private BigInteger matchId;
    private String playerName;
    private boolean isRadiant;
    private int netWorth;
    private int playerSlot;
    private int kills;
    private int deaths;
    private int assists;
    private int heroLevel;
    private int heroDamage;
    private int towerDamage;
    private Hero hero;
    private List<Item> items;
    private List<Item> backpackedItems;
    private Item neutral;
    private MatchDetails matchDetails;

    public BigInteger getPlayerId() {
        return playerId;
    }

    public void setPlayerId(BigInteger playerId) {
        this.playerId = playerId;
    }

    public BigInteger getMatchId() {
        return matchId;
    }

    public void setMatchId(BigInteger matchId) {
        this.matchId = matchId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public boolean isRadiant() {
        return isRadiant;
    }

    public void setRadiant(boolean radiant) {
        isRadiant = radiant;
    }

    public int getNetWorth() {
        return netWorth;
    }

    public void setNetWorth(int netWorth) {
        this.netWorth = netWorth;
    }

    public int getPlayerSlot() {
        return playerSlot;
    }

    public void setPlayerSlot(int playerSlot) {
        this.playerSlot = playerSlot;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public int getHeroLevel() {
        return heroLevel;
    }

    public void setHeroLevel(int heroLevel) {
        this.heroLevel = heroLevel;
    }

    public int getHeroDamage() {
        return heroDamage;
    }

    public void setHeroDamage(int heroDamage) {
        this.heroDamage = heroDamage;
    }

    public int getTowerDamage() {
        return towerDamage;
    }

    public void setTowerDamage(int towerDamage) {
        this.towerDamage = towerDamage;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public List<Item> getBackpackedItems() {
        return backpackedItems;
    }

    public void setBackpackedItems(List<Item> backpackedItems) {
        this.backpackedItems = backpackedItems;
    }

    public Item getNeutral() {
        return neutral;
    }

    public void setNeutral(Item neutral) {
        this.neutral = neutral;
    }

    public MatchDetails getMatchDetails() {
        return matchDetails;
    }

    public void setMatchDetails(MatchDetails matchDetails) {
        this.matchDetails = matchDetails;
    }

    @Override
    public String toString() {
        return "PlayerDetailsDTO{" +
                "playerId=" + playerId +
                ", matchId=" + matchId +
                ", playerName='" + playerName + '\'' +
                ", isRadiant=" + isRadiant +
                ", netWorth=" + netWorth +
                ", playerSlot=" + playerSlot +
                ", kills=" + kills +
                ", deaths=" + deaths +
                ", assists=" + assists +
                ", heroLevel=" + heroLevel +
                ", heroDamage=" + heroDamage +
                ", towerDamage=" + towerDamage +
                ", hero=" + hero +
                ", items=" + items +
                ", backpackedItems=" + backpackedItems +
                ", neutral=" + neutral +
                ", matchDetails=" + matchDetails +
                '}';
    }
}
