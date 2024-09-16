package com.example.AlbertStats.Services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.sql.Array;
import java.util.*;
import java.util.logging.Logger;

@Service
public class DotaApiReadingService {
    private final String playerID;
    private final RestTemplate restTemplate;
    private final Logger logger = Logger.getLogger(DotaApiReadingService.class.getName());

    public DotaApiReadingService(RestTemplate restTemplate,
                                 @Value("${dota2.accountId}") String playerID) {
        this.restTemplate = restTemplate;
        this.playerID = playerID;
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
        catch (JsonMappingException e) {
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
    public void readMatchDetails() {

    }

}
