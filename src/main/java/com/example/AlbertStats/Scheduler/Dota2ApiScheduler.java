package com.example.AlbertStats.Scheduler;

import com.example.AlbertStats.Repository.Dota2Repo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;



@Component
public class Dota2ApiScheduler {//Basic concept, still not finished

    private final Dota2Repo dota2Repo;
    private final RestTemplate rest;

    @Value("${dota2.accountId}")
    private String account_id;
    public Dota2ApiScheduler(Dota2Repo dota2Repo, RestTemplate rest) {//
        this.dota2Repo = dota2Repo;
        this.rest = rest;
    }
    //TODO
    public void databaseChecking() {//I want to call the api and make sure the application is up to date
        //Step one, create database table if not create
        //step two, make sure database is up to date, havent figured out method yet, most likely check latest in the api call and latest in database, and check last index in from the database,
        //https://api.opendota.com/api/players/111477708/matches
        //Step three, if all up to date, this method is done,
        //step four, if not up
    }
    public boolean isLatestTheSame() {
        return true;
    }

}
