package com.example.AlbertStats.Entities;

import java.util.List;

public class PlayerDetails {
    private int id;
    private int matchId;//TODO check if this needs changing
    private String playerName;
    private String heroName;//hero the player is playing
    private boolean isRadiant;
    private int netWorth;
    private int playerSlot;
    private int kills;
    private int deaths;
    private int assists;
    private int heroLevel;
    private int heroDamage;
    private int towerDamage;
    private List<Item> items;
    private List<Item> backpackedItems;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
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
}
