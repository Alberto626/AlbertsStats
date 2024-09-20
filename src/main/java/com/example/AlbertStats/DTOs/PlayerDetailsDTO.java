package com.example.AlbertStats.DTOs;

import com.example.AlbertStats.Entities.Hero;
import com.example.AlbertStats.Entities.Item;
import com.example.AlbertStats.Entities.MatchDetails;

import java.util.List;

public class PlayerDetailsDTO {//This will be like playerMatchDetails but all the info
    private int matchId;
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

}
