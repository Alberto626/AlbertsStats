package com.example.AlbertStats.DTOs;

import java.math.BigInteger;
public class PlayerMatchDetailsDTO {//This object is from api call about match details,
    private BigInteger id;
    private BigInteger matchId;//TODO check if this needs changing
    private String playerName;
    private int heroId;//hero the player is playing
    private boolean isRadiant;
    private int netWorth;
    private int playerSlot;
    private int kills;
    private int deaths;
    private int assists;
    private int heroLevel;
    private int heroDamage;
    private int towerDamage;
    private int item0Id;
    private int item1Id;
    private int item2Id;
    private int item3Id;
    private int item4Id;
    private int item5Id;
    private int backpack0Id;
    private int backpack1Id;
    private int backpack2Id;
    private int itemNeutral;

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

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getHeroName() {
        return heroId;
    }

    public void setHeroId(int heroId) {
        this.heroId = heroId;
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

    public int getItem0Id() {
        return item0Id;
    }

    public void setItem0Id(int item0Id) {
        this.item0Id = item0Id;
    }

    public int getItem1Id() {
        return item1Id;
    }

    public void setItem1Id(int item1Id) {
        this.item1Id = item1Id;
    }

    public int getItem2Id() {
        return item2Id;
    }

    public void setItem2Id(int item2Id) {
        this.item2Id = item2Id;
    }

    public int getItem3Id() {
        return item3Id;
    }

    public void setItem3Id(int item3Id) {
        this.item3Id = item3Id;
    }

    public int getItem4Id() {
        return item4Id;
    }

    public void setItem4Id(int item4Id) {
        this.item4Id = item4Id;
    }

    public int getItem5Id() {
        return item5Id;
    }

    public void setItem5Id(int item5Id) {
        this.item5Id = item5Id;
    }

    public int getBackpack0Id() {
        return backpack0Id;
    }

    public void setBackpack0Id(int backpack0Id) {
        this.backpack0Id = backpack0Id;
    }

    public int getBackpack1Id() {
        return backpack1Id;
    }

    public void setBackpack1Id(int backpack1Id) {
        this.backpack1Id = backpack1Id;
    }

    public int getBackpack2Id() {
        return backpack2Id;
    }

    public void setBackpack2Id(int backpack2Id) {
        this.backpack2Id = backpack2Id;
    }

    public int getItemNeutral() {
        return itemNeutral;
    }

    public void setItemNeutral(int itemNeutral) {
        this.itemNeutral = itemNeutral;
    }

    @Override
    public String toString() {
        return "PlayerMatchDetailsDTO{" +
                "id=" + id +
                ", matchId=" + matchId +
                ", playerName='" + playerName + '\'' +
                ", heroId=" + heroId +
                ", isRadiant=" + isRadiant +
                ", netWorth=" + netWorth +
                ", playerSlot=" + playerSlot +
                ", kills=" + kills +
                ", deaths=" + deaths +
                ", assists=" + assists +
                ", heroLevel=" + heroLevel +
                ", heroDamage=" + heroDamage +
                ", towerDamage=" + towerDamage +
                ", item0Id=" + item0Id +
                ", item1Id=" + item1Id +
                ", item2Id=" + item2Id +
                ", item3Id=" + item3Id +
                ", item4Id=" + item4Id +
                ", item5Id=" + item5Id +
                ", backpack0Id=" + backpack0Id +
                ", backpack1Id=" + backpack1Id +
                ", backpack2Id=" + backpack2Id +
                ", itemNeutral=" + itemNeutral +
                '}';
    }
}
