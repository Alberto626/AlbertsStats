package com.example.AlbertStats.Services;

import com.example.AlbertStats.Entities.Hero;
import com.example.AlbertStats.Entities.Item;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    private final Resource heroesResource;
    private final Resource itemsResource;
    private Logger logger = Logger.getLogger(ReadJsonFilesService.class.getName());

    private JsonNode nodeHeroes;
    private JsonNode nodeItems;

    private List<Hero> heroes;
    private List<Item> items;

    public ReadJsonFilesService(@Value("classpath:data/heroes.json") Resource heroesResource,
                                @Value("classpath:data/items.json") Resource itemsResource) {
        this.heroesResource = heroesResource;
        this.itemsResource = itemsResource;
        setNodeHeroes();
        setNodeItems();
        setHeroes();
        setItems();
    }

    private void setNodeHeroes() {
        try {
            JsonNode jsonNode = new ObjectMapper().readTree(heroesResource.getFile());
            nodeHeroes = jsonNode;
        }
        catch(IOException e) {//TODO find a way to treat error
            logger.info("CANNOT READ FROM heroes.json");
        }
    }
    private void setNodeItems() {
        try {
            JsonNode jsonNode = new ObjectMapper().readTree(itemsResource.getFile());
            nodeItems = jsonNode;
        }
        catch(IOException e) {//TODO find a way to treat error
            logger.info("CANNOT READ FROM items.json");
        }
    }
    private void setHeroes() {
        Iterator<JsonNode> iterator = nodeHeroes.elements();
        List<Hero> heroes = jsonToHeroes(iterator);
        this.heroes = heroes;
    }
    public void setItems() {
        Iterator<JsonNode> iterator1 = nodeItems.elements();
        List<Item> items = jsonToItems(iterator1);
        this.items = items;
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
    private List<Item> jsonToItems(Iterator iterator) {//todo certain key values dont have the same field names, need to find the which ones
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
                    description = j.get("description").asText().replace("\n", "");//remove all \n letters
                }
            }
            item.setDescription(description);
            items.add(item);
        }
        return items;
    }

    public List<Hero> getHeroes() {//these contain the organized information that I want for the mysql database
        return heroes;
    }

    public List<Item> getItems() {
        return items;
    }

    public JsonNode getNodeHeroes() {//This is the raw file that contains the json file information but unorganized
        return nodeHeroes;
    }

    public JsonNode getNodeItems() {
        return nodeItems;
    }

}
