package com.example.AlbertStats.Scheduler.JsonReading;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

@Service
public class JsonFilesToListService {
    @Value("classpath:data/heroes.json")
    private Resource heroes;
    @Value("classpath:data/items.json")
    private Resource items;
    private final Logger logger = Logger.getLogger(JsonFilesToListService.class.getName());

    public List<Hero> listOfHeroes() {
        try {
            JsonNode heroesNode = new ObjectMapper().readTree(heroes.getFile());
            Iterator<JsonNode> iterator = heroesNode.elements();
            List<Hero> heroes = jsonToHeroes(iterator);
            return heroes;
        }
        catch (IOException e) {
            logger.info("Cannot read from File");
        }
        return null;
    }
    public List<Item> listOfItems() {
        try {
            JsonNode itemsNode = new ObjectMapper().readTree(items.getFile());
            Iterator<JsonNode> iterator1 = itemsNode.elements();
            List<Item> items = jsonToItems(iterator1);
            return items;
        }
        catch (IOException e) {
            logger.info("Cannot read from Items.json");
        }
        return null;
    }
    private List<Hero> jsonToHeroes(Iterator iterator) {
        List<Hero> heroes = new ArrayList<>();
        while(iterator.hasNext()) {
            JsonNode current = (JsonNode) iterator.next();
            Hero hero = new Hero();
            hero.setId(current.get("id").asInt());
            hero.setName(current.get("localized_name").asText());
            hero.setPrimaryAttribute(current.get("primary_attr").asText());

            JsonNode roles = current.get("roles");
            List<String> l = new ArrayList<>();
            if(roles.isArray()) {
                for(JsonNode jNode: roles) {
                    l.add(jNode.toString());
                }
                hero.setRoles(l);
            }
            heroes.add(hero);
        }
        return heroes;
    }
    private List<Item> jsonToItems(Iterator iterator) {
        List<Item> items = new ArrayList<>();
        while(iterator.hasNext()) {
            JsonNode current = (JsonNode) iterator.next();
            Item item = new Item();
            item.setId(current.get("id").asInt());
            item.setCost(current.get("cost").asInt());
            if(current.has("dname")) { //one of the items does not have this a dname for some reason
                item.setName(current.get("dname").asText());
            }
            else {
                item.setName("NO NAME");
            }

            String description = "NO DESCRIPTION";
            if(current.has("abilities") && current.get("abilities").isArray()) {
                for(JsonNode j: current.get("abilities")) {
                    description = j.get("description").asText();
                }
            }
            item.setDescription(description);
            items.add(item);
        }
        return items;
    }
}

//Description
//THis class has a very purpose of converting entire json file into a Objects needed for mySqlDatabase
