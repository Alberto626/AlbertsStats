package com.example.AlbertStats.Scheduler;

import com.example.AlbertStats.Repository.Dota2Repo;
import com.example.AlbertStats.Services.DotaDataComparisonService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;



@Component //TODO change to service after reading more about it
public class Dota2ApiScheduler {//Basic concept, still not finished

    private final Dota2Repo dota2Repo;
    private final RestTemplate rest;
    private String accountId;
    private final DotaDataComparisonService dotaDataComparisonService;

    public Dota2ApiScheduler(Dota2Repo dota2Repo,
                             RestTemplate rest,
                             @Value("${dota2.accountId}") String accountId,
                             DotaDataComparisonService dotaDataComparisonService) {//
        this.dota2Repo = dota2Repo;
        this.rest = rest;
        this.accountId = accountId;
        this.dotaDataComparisonService = dotaDataComparisonService;
    }
    //TODO
    public void updateHeroesToDatabase() {//update heroes to our database every once in a while
        dotaDataComparisonService.updateHeroes();
    }
    public void updateItemsToDatabase() {//this will update items when its empty every once in a while
        dotaDataComparisonService.updateItems();
    }

}
