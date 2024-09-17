package com.example.AlbertStats.Scheduler;

import com.example.AlbertStats.Repository.Dota2Repo;
import com.example.AlbertStats.Services.DotaDataComparisonService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;



@Component //TODO this might be a service
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

    public void updateHeroesToDatabase() {//update heroes to our database every once in a while
        dotaDataComparisonService.updateHeroes();
    }

    public void updateItemsToDatabase() {//Read from my json file and update to the mysql database
        dotaDataComparisonService.updateItems();
    }

}
