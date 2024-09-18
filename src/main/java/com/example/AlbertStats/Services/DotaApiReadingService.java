package com.example.AlbertStats.Services;

import com.example.AlbertStats.DTOs.PlayerMatchDetailsDTO;
import com.example.AlbertStats.Entities.MatchDetails;
import com.example.AlbertStats.Repository.Dota2Repo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.math.BigInteger;
import java.util.*;
import java.util.logging.Logger;

@Service
public class DotaApiReadingService {
    private final String playerID;
    private final RestTemplate restTemplate;
    private final Dota2Repo dota2Repo;
    private final Logger logger = Logger.getLogger(DotaApiReadingService.class.getName());

    public DotaApiReadingService(RestTemplate restTemplate,
                                 @Value("${dota2.accountId}") String playerID,
                                 Dota2Repo dota2Repo) {
        this.restTemplate = restTemplate;
        this.playerID = playerID;
        this.dota2Repo = dota2Repo;
    }
    public List<BigInteger> readAllMatches() {//get all match ids
        try {
            ResponseEntity<String> response = restTemplate
                    .getForEntity("https://api.opendota.com/api/players/" + playerID +"/matches", String.class);
            JsonNode node = new ObjectMapper().readTree(response.getBody());
            List<BigInteger> matchIDs = getAllMatchesIDs(node.elements());
            logger.info("" +matchIDs.size());
            return matchIDs;
        }
        catch (JsonMappingException e) {//TODO what to do with these exceptions
            logger.info("mapping exception ain't working");
        } catch (JsonProcessingException e) {
            logger.info("processing exception ain't working");
        }
        return null;
    }
    private List<BigInteger> getAllMatchesIDs(Iterator<JsonNode> elements) {
        Stack<BigInteger> stack = new Stack<>();
        while(elements.hasNext()) {
            JsonNode currentNode = elements.next();
            BigInteger matchId = new BigInteger(currentNode.get("match_id").asText());
            stack.push(matchId);
        }
        return stack;
    }
    public void readMatchDetails(BigInteger matchId) {//TODO parameter might be either string or long
        try {
            ResponseEntity<String> response =restTemplate
                    .getForEntity("https://api.opendota.com/api/matches/" + matchId, String.class);
            if(!response.getStatusCode().is2xxSuccessful()) {//if our response is not a 200 then we got a problem to deal with
                throw new RestClientException("This aint a 200 status code");
            }
            JsonNode node = new ObjectMapper().readTree(response.getBody());//This is the
            addMatchDetails(node, matchId);

        }
        catch (JsonMappingException e) {//TODO write how to handle exceptions
            logger.info("mapping exception ain't working");
        } catch (JsonProcessingException e) {
            logger.info("processing exception ain't working");
        }
        catch(RestClientException e) {
            logger.info("rest template exception" + e);
        }
    }
    private void addMatchDetails(JsonNode node, BigInteger matchId) {//this will map all the details of our response to mysql
        MatchDetails matchDetails = new MatchDetails();
        int duration = node.get("duration").asInt();
        boolean radiantVictory = node.get("radiant_win").asBoolean();
        //id will be auto incremented
        matchDetails.setRadiantVictory(radiantVictory);
        matchDetails.setMatchDuration(duration);
        matchDetails.setMatchId(matchId);
        dota2Repo.addMatchDetails(matchDetails);

        addPlayersDetails(node.get("players"), matchId);
    }
    private void addPlayersDetails(JsonNode players, BigInteger matchId) {
        Iterator<JsonNode> iterator = players.elements();
        while(iterator.hasNext()) {
            JsonNode player = iterator.next();

            String playerName = player.has("personaname") ? player.get("personaname").asText() : "Anonymous";//some players are opted out from giving their info away, so we have to give them an anonymous name
            int heroId = player.get("hero_id").asInt();
            boolean isRadiant = player.get("isRadiant").asBoolean();
            int netWorth = player.get("net_worth").asInt();
            int playerSlot = player.get("player_slot").asInt();
            int kills = player.get("kills").asInt();
            int deaths = player.get("deaths").asInt();
            int assists = player.get("assists").asInt();
            int heroLevel = player.get("level").asInt();
            int heroDamage = player.get("hero_damage").asInt();
            int towerDamage = player.get("tower_damage").asInt();
            int item0Id = player.get("item_0").asInt();
            int item1Id = player.get("item_1").asInt();
            int item2Id = player.get("item_2").asInt();
            int item3Id = player.get("item_3").asInt();
            int item4Id = player.get("item_4").asInt();
            int item5Id = player.get("item_5").asInt();
            int backpack0Id = player.get("backpack_0").asInt();
            int backpack1Id = player.get("backpack_1").asInt();
            int backpack2Id = player.get("backpack_2").asInt();
            int itemNeutral = player.get("item_neutral").asInt();

            PlayerMatchDetailsDTO playerMatchDetailsDTO = new PlayerMatchDetailsDTO();
            playerMatchDetailsDTO.setMatchId(matchId);
            playerMatchDetailsDTO.setPlayerName(playerName);
            playerMatchDetailsDTO.setHeroId(heroId);
            playerMatchDetailsDTO.setRadiant(isRadiant);
            playerMatchDetailsDTO.setNetWorth(netWorth);
            playerMatchDetailsDTO.setPlayerSlot(playerSlot);
            playerMatchDetailsDTO.setKills(kills);
            playerMatchDetailsDTO.setDeaths(deaths);
            playerMatchDetailsDTO.setAssists(assists);
            playerMatchDetailsDTO.setHeroLevel(heroLevel);
            playerMatchDetailsDTO.setHeroDamage(heroDamage);
            playerMatchDetailsDTO.setTowerDamage(towerDamage);
            playerMatchDetailsDTO.setItem0Id(item0Id);
            playerMatchDetailsDTO.setItem1Id(item1Id);
            playerMatchDetailsDTO.setItem2Id(item2Id);
            playerMatchDetailsDTO.setItem3Id(item3Id);
            playerMatchDetailsDTO.setItem4Id(item4Id);
            playerMatchDetailsDTO.setItem5Id(item5Id);
            playerMatchDetailsDTO.setBackpack0Id(backpack0Id);
            playerMatchDetailsDTO.setBackpack1Id(backpack1Id);
            playerMatchDetailsDTO.setBackpack2Id(backpack2Id);
            playerMatchDetailsDTO.setItemNeutral(itemNeutral);
            dota2Repo.addPlayerDetails(playerMatchDetailsDTO);
        }
    }
}

