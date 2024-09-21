package com.example.AlbertStats.Repository.RowMappers;

import com.example.AlbertStats.DTOs.PlayerDetailsDTO;
import com.example.AlbertStats.Entities.Hero;
import com.example.AlbertStats.Entities.Item;
import com.example.AlbertStats.Entities.MatchDetails;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigInteger;
import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlayerDetailsDtoRowMapper implements RowMapper<PlayerDetailsDTO> {
    @Override
    public PlayerDetailsDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        PlayerDetailsDTO playerDetailsDTO = new PlayerDetailsDTO();
        MatchDetails matchDetails = new MatchDetails();
        List<Item> items = new ArrayList<>(6);//TODO find if theres a better data structure to use
        List<Item> backpackItems = new ArrayList<>(3); //TODO

        String playerName = rs.getString("player_name");
        BigInteger playerId = BigInteger.valueOf(rs.getLong("player_id"));
        boolean isRadiant = rs.getBoolean("is_radiant");
        int netWorth = rs.getInt("net_worth");
        int playerSlot = rs.getInt("player_slot");
        int kills = rs.getInt("kills");
        int deaths = rs.getInt("deaths");
        int assists = rs.getInt("assists");
        int heroLevel = rs.getInt("hero_level");
        int heroDamage = rs.getInt("hero_damage");
        int towerDamage = rs.getInt("tower_damage");

        BigInteger bigInteger = BigInteger.valueOf(rs.getLong("id"));
        BigInteger matchId = BigInteger.valueOf(rs.getLong("match_id"));
        boolean radiantVictory = rs.getBoolean("radiant_victory");
        int matchDuration = rs.getInt("match_duration_seconds");
        matchDetails.setId(bigInteger);
        matchDetails.setMatchId(matchId);
        matchDetails.setRadiantVictory(radiantVictory);
        matchDetails.setMatchDuration(matchDuration);
        //set matchDetails

        Hero hero = new Hero();
        int heroId = rs.getInt("hero_id");
        String heroName = rs.getString("hero_name");
        String primaryAttribute = rs.getString("primary_attribute");
        List<String> roles = Arrays.stream(rs.getString("roles").replaceAll("\"", "").split(",")).toList(); //remove all the quotes and replace it with nothing, then split it into a array which turns to a list
        hero.setId(heroId);
        hero.setName(heroName);
        hero.setRoles(roles);
        hero.setPrimaryAttribute(primaryAttribute);

        Item item0 = new Item();
        int item0Id = rs.getInt("item0_id");
        String item0Name = rs.getString("item0_name");
        String item0Description = rs.getString("item0_description");
        int item0Cost = rs.getInt("item0_cost");
        item0.setId(item0Id);
        item0.setName(item0Name);
        item0.setDescription(item0Description);
        item0.setCost(item0Cost);

        Item item1 = new Item();
        int item1Id = rs.getInt("item1_id");
        String item1Name = rs.getString("item1_name");
        String item1Description = rs.getString("item1_description");
        int item1Cost = rs.getInt("item1_cost");
        item1.setId(item1Id);
        item1.setName(item1Name);
        item1.setDescription(item1Description);
        item1.setCost(item1Cost);

        Item item2 = new Item();
        int item2Id = rs.getInt("item2_id");
        String item2Name = rs.getString("item2_name");
        String item2Description = rs.getString("item2_description");
        int item2Cost = rs.getInt("item2_cost");
        item2.setId(item2Id);
        item2.setName(item2Name);
        item2.setDescription(item2Description);
        item2.setCost(item2Cost);

        Item item3 = new Item();
        int item3Id = rs.getInt("item3_id");
        String item3Name = rs.getString("item3_name");
        String item3Description = rs.getString("item3_description");
        int item3Cost = rs.getInt("item3_cost");
        item3.setId(item3Id);
        item3.setName(item3Name);
        item3.setDescription(item3Description);
        item3.setCost(item3Cost);

        Item item4 = new Item();
        int item4Id = rs.getInt("item4_id");
        String item4Name = rs.getString("item4_name");
        String item4Description = rs.getString("item4_description");
        int item4Cost = rs.getInt("item4_cost");
        item4.setId(item4Id);
        item4.setName(item4Name);
        item4.setDescription(item4Description);
        item4.setCost(item4Cost);

        Item item5 = new Item();
        int item5Id = rs.getInt("item5_id");
        String item5Name = rs.getString("item5_name");
        String item5Description = rs.getString("item5_description");
        int item5Cost = rs.getInt("item5_cost");
        item5.setId(item5Id);
        item5.setName(item5Name);
        item5.setDescription(item5Description);
        item5.setCost(item5Cost);

        items.add(item0);
        items.add(item1);
        items.add(item2);
        items.add(item3);
        items.add(item4);
        items.add(item5);

        Item backpackItem0 = new Item();
        int backpackItem0Id = rs.getInt("backpack0_id");
        String backpackItem0Name = rs.getString("backpack0_name");
        String backpackItem0Description = rs.getString("backpack0_description");
        int backpackItem0Cost = rs.getInt("backpack0_cost");
        backpackItem0.setId(backpackItem0Id);
        backpackItem0.setName(backpackItem0Name);
        backpackItem0.setDescription(backpackItem0Description);
        backpackItem0.setCost(backpackItem0Cost);

        Item backpackItem1 = new Item();
        int backpackItem1Id = rs.getInt("backpack1_id");
        String backpackItem1Name = rs.getString("backpack1_name");
        String backpackItem1Description = rs.getString("backpack1_description");
        int backpackItem1Cost = rs.getInt("backpack1_cost");
        backpackItem1.setId(backpackItem1Id);
        backpackItem1.setName(backpackItem1Name);
        backpackItem1.setDescription(backpackItem1Description);
        backpackItem1.setCost(backpackItem1Cost);

        Item backpackItem2 = new Item();
        int backpackItem2Id = rs.getInt("backpack0_id");
        String backpackItem2Name = rs.getString("backpack0_name");
        String backpackItem2Description = rs.getString("backpack0_description");
        int backpackItem2Cost = rs.getInt("backpack0_cost");
        backpackItem2.setId(backpackItem2Id);
        backpackItem2.setName(backpackItem2Name);
        backpackItem2.setDescription(backpackItem2Description);
        backpackItem2.setCost(backpackItem2Cost);

        backpackItems.add(backpackItem0);
        backpackItems.add(backpackItem1);
        backpackItems.add(backpackItem2);

        Item neutral = new Item();
        int neutralId = rs.getInt("neutral_id");
        String neutralName = rs.getString("neutral_name");
        String neutralDescription = rs.getString("neutral_description");
        String neutralCost = rs.getString("neutral_cost");

        playerDetailsDTO.setMatchDetails(matchDetails);
        playerDetailsDTO.setBackpackedItems(backpackItems);
        playerDetailsDTO.setItems(items);
        playerDetailsDTO.setPlayerName(playerName);
        playerDetailsDTO.setPlayerId(playerId);
        playerDetailsDTO.setRadiant(isRadiant);
        playerDetailsDTO.setNetWorth(netWorth);
        playerDetailsDTO.setPlayerSlot(playerSlot);
        playerDetailsDTO.setKills(kills);
        playerDetailsDTO.setDeaths(deaths);
        playerDetailsDTO.setAssists(assists);
        playerDetailsDTO.setHeroLevel(heroLevel);
        playerDetailsDTO.setHeroDamage(heroDamage);
        playerDetailsDTO.setTowerDamage(towerDamage);
        playerDetailsDTO.setMatchId(matchId);
        playerDetailsDTO.setHero(hero);
        playerDetailsDTO.setNeutral(neutral);
        return playerDetailsDTO;
    }
}
