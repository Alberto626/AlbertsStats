package com.example.AlbertStats.DTOs;

import com.example.AlbertStats.Entities.Item;

import java.math.BigInteger;
import java.util.List;

public class PlayerMatchDetailsDTO {//This object is from api call about match details,
    private BigInteger id;
    private BigInteger matchId;//TODO check if this needs changing
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
}
