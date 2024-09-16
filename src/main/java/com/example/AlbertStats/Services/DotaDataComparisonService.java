package com.example.AlbertStats.Services;

import com.example.AlbertStats.Repository.Dota2Repo;
import com.example.AlbertStats.Entities.Hero;
import com.example.AlbertStats.Entities.Item;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class DotaDataComparisonService {

    private final ReadJsonFilesService readJsonFilesService;
    private Logger logger = Logger.getLogger(DotaDataComparisonService.class.getName());
    private Dota2Repo dota2Repo;

    public DotaDataComparisonService(ReadJsonFilesService readJsonFilesService, Dota2Repo dota2Repo) {
        this.dota2Repo = dota2Repo;
        this.readJsonFilesService = readJsonFilesService;
    }
    public boolean isLatestHeroCurrent() { //TODO make some changes to make comparisons of records of json and mysql to update
        if(latestJsonHero().equals(latestMySqlHero())) {
            return true;
        }
        return false;
    }
    public boolean isLatestItemCurrent() {
        if(latestJsonItem().equals(latestMySqlItem())) {
            return true;
        }
        return false;
    }
    public void updateHeroes() {
        for(Hero hero: readJsonFilesService.getHeroes()) {
            dota2Repo.replaceHero(hero);
        }
    }
    public void updateItems() {
        for(Item item: readJsonFilesService.getItems()) {
            dota2Repo.replaceItem(item);
        }
    }
    public boolean hasHeroRecords() { //THis is supposed to check if any records at all in the database because
        return hasHeroRecords();
    }
    public boolean hasItemRecords() {
        return hasItemRecords();
    }
    private Hero latestJsonHero() {
        Hero hero = readJsonFilesService.getHeroes().get(readJsonFilesService.getHeroes().size() -1); //return last one
        return hero;
    }
    private Item latestJsonItem() {
        Item item = readJsonFilesService.getItems().get(readJsonFilesService.getItems().size() -1);
        return item;
    }
    private Hero latestMySqlHero() {
        Hero hero = dota2Repo.getLatestHero();
        return hero;
    }
    private Item latestMySqlItem() {
        Item item = dota2Repo.getLatestItem();
        return item;
    }
}
//This class is about checking the latest data from the json to the latest in the mysql