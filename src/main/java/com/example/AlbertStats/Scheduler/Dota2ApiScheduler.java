package com.example.AlbertStats.Scheduler;

import com.example.AlbertStats.Repository.Dota2Repo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

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
    @Scheduled(cron = "0 */5 * ? * *") //every 5 minutes
    public void callAPI() {

    }
    @Scheduled
    public void isDatabaseEmpty() {//one time use when starting application

    }
    private List<?> callApi() {//this will grab every data from the database
        return null;
    }
    private boolean compareData() {//compare the api information and with database useing jdbc
        return true;
    }
    private void addData() { //add to our Database ONLY after we compare
    }
}
