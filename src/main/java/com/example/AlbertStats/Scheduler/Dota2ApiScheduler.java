package com.example.AlbertStats.Scheduler;

import com.example.AlbertStats.Repository.Dota2Repo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;



@Component
public class Dota2ApiScheduler {//Basic concept, still not finished

    private final Dota2Repo dota2Repo;
    private final RestTemplate rest;
    private String accountId;
    public Dota2ApiScheduler(Dota2Repo dota2Repo,
                             RestTemplate rest,
                             @Value("${dota2.accountId}") String accountId) {//
        this.dota2Repo = dota2Repo;
        this.rest = rest;
        this.accountId = accountId;

    }
    //TODO
    public void databaseChecking() {//I want to call the api and make sure the application is up to date

    }
    public boolean isLatestTheSame() {
        return true;
    }

}
