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
public class ReadJsonFilesService {
    @Value("classpath:data/heroes.json")
    private Resource heroes;
    @Value("classpath:data/items.json")
    private Resource items;
    private final Logger logger = Logger.getLogger(ReadJsonFilesService.class.getName());

    @PostConstruct //purely for testing reasons
    public void readJson() {
        try {
            JsonNode heroesNode = new ObjectMapper().readTree(heroes.getFile());
            JsonNode itemsNode = new ObjectMapper().readTree(items.getFile());

            Iterator<JsonNode> iterator = heroesNode.elements();
            Iterator<JsonNode> iterator1 = itemsNode.elements();

            List<Hero> heroes = listOfHeroes(iterator);
            List<Item> items = listOfItems(iterator1);
        }
        catch (IOException e) {
            //TODOdo something
        }
    }
    private List<Hero> listOfHeroes(Iterator iterator) {
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
        logger.info(heroes.get(heroes.size() -1).toString());
        return heroes;
    }
    private List<Item> listOfItems(Iterator iterator) {
        List<Item> items = new ArrayList<>();
        while(iterator.hasNext()) {
            JsonNode current = (JsonNode) iterator.next();
            Item item = new Item();
            item.setId(current.get("id").asInt());
            item.setCost(current.get("cost").asInt());
            if(current.has("dname")) { //one of the items does not have this a dname for some reason
                item.setName(current.get("dname").asText());
            }
            JsonNode
            logger.info(item.toString());
        }
        return null;
    }
}

//Description
//THis class has a very purpose of converting entire json file into a Objects needed for mySqlDatabase
