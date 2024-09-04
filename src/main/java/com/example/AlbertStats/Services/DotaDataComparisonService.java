package com.example.AlbertStats.Services;

import com.example.AlbertStats.Repository.Dota2Repo;
import com.example.AlbertStats.Scheduler.JsonReading.Hero;
import com.example.AlbertStats.Scheduler.JsonReading.Item;
import org.springframework.stereotype.Service;
import java.util.logging.Logger;

@Service
public class DotaDataComparisonService {

    private final ReadJsonFilesService readJsonFilesService;
    private Logger logger = Logger.getLogger(DotaDataComparisonService.class.getName());
    private Dota2Repo dota2Repo;

    public DotaDataComparisonService(ReadJsonFilesService readJsonFilesService) {
        this.readJsonFilesService = readJsonFilesService;
    }
    public boolean isLatestHeroCurrent() { //compare sql to json readings, if they are different then i need to make some changes
        if(latestJsonHero().equals(latestMySqlHero())) {
            return true;
        }
        return false;
    }
    public boolean hasRecords() { //THis is supposed to check if any records at all in the database because

        return false;
    }
    public boolean isLatestItemCurrent() {
        if(latestJsonItem().equals(latestMySqlItem())) {
            return true;
        }
        return false;
    }
    private Hero latestJsonHero() {
        Hero hero = readJsonFilesService.getHeroes().get(readJsonFilesService.getHeroes().size() -1); //return last one
        return hero;
    }
    private Item latestJsonItem() {
        Item item = readJsonFilesService.getItems().get(readJsonFilesService.getItems().size() -1);
        return item;
    }
    private Hero latestMySqlHero() {//TODO requires repo
        Hero hero = dota2Repo.getLatestHero();
        return hero;
    }
    private Item latestMySqlItem() {
        Item item = latestMySqlItem();
        return item;
    }
}
//This class is about checking the latest data from the json to the latest in the mysql