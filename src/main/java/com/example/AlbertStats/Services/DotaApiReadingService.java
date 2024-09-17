package com.example.AlbertStats.Services;

import com.example.AlbertStats.Repository.Dota2Repo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
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
    public List<Long> readAllMatches() {//get all match ids
        try {
            ResponseEntity<String> response = restTemplate
                    .getForEntity("https://api.opendota.com/api/players/" + playerID +"/matches", String.class);
            JsonNode node = new ObjectMapper().readTree(response.getBody());
            List<Long> matchIDs = getAllMatchesIDs(node.elements());
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
    private List<Long> getAllMatchesIDs(Iterator<JsonNode> elements) {
        Stack<Long> stack = new Stack<>();
        while(elements.hasNext()) {
            JsonNode currentNode = elements.next();
            long matchId = currentNode.get("match_id").asLong();
            stack.push(matchId);
        }
        return stack;
    }
    @PostConstruct
    public void readMatchDetails() {//TODO parameter might be either string or long
        long matchId = 0;
        try {
            ResponseEntity<String> response =restTemplate
                    .getForEntity("https://api.opendota.com/api/matches/" + matchId, String.class);
            if(!response.getStatusCode().is2xxSuccessful()) {//if our response is not a 200 then we got a problem to deal with
                throw new RestClientException("This aint a 200 status code");
            }
            JsonNode node = new ObjectMapper().readTree(response.getBody());
            mapDetails(node, matchId);

        }
        catch (JsonMappingException e) {//TODO write how to handle exceptions
            logger.info("mapping exception ain't working");
        } catch (JsonProcessingException e) {
            logger.info("processing exception ain't working");
        }
        catch(RestClientException e) {
            logger.info("rest template exception" + e.toString());
        }
    }
    private void mapDetails(JsonNode node, long matchId) {//this will map all the details of our response to mysql
        Iterator<JsonNode> iterator = node.elements();
        while(iterator.hasNext()) {
            JsonNode currentNode = iterator.next();
            if(currentNode.has("players") && currentNode.get("players").isArray()) {
                for(JsonNode player: currentNode.get("players")) {
                    String playerName = currentNode.has("personaname") ? currentNode.get("personaname").asText() : "Anonymous";//some players are opted out from giving their info away, so we have to give them an anonymous name
                    int heroId = currentNode.get("hero_id").asInt();
                    boolean isRadiant = currentNode.get("isRadiant").asBoolean();
                    int netWorth = currentNode.get("net_worth").asInt();
                    int playerSlot = currentNode.get("player_slot").asInt();
                    int kills = currentNode.get("kills").asInt();
                    int deaths = currentNode.get("deaths").asInt();
                    int assists = currentNode.get("assists").asInt();
                    int heroLevel = currentNode.get("level").asInt();
                    int heroDamage = currentNode.get("hero_damage").asInt();
                    int towerDamage = currentNode.get("tower_damage").asInt();
                    int item0Id = currentNode.get("item_0").asInt();
                    int item1Id = currentNode.get("item_1").asInt();
                    int item2Id = currentNode.get("item_2").asInt();
                    int item3Id = currentNode.get("item_3").asInt();
                    int item4Id = currentNode.get("item_4").asInt();
                    int item5Id = currentNode.get("item_5").asInt();
                    int backpack0Id = currentNode.get("backpack_0").asInt();
                    int backpack1Id = currentNode.get("backpack_1").asInt();
                    int backpack2Id = currentNode.get("backpack_2").asInt();
                    int itemNeutral = currentNode.get("item_neutral").asInt();

                }
            }
        }
    }

}
