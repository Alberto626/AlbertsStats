package com.example.AlbertStats.Scheduler;

import com.example.AlbertStats.Repository.Dota2Repo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class Dota2ApiScheduler {//Basic concept, still not finished

    private final Dota2Repo dota2Repo;

    public Dota2ApiScheduler(Dota2Repo dota2Repo) {//
        this.dota2Repo = dota2Repo;
    }
    public void callAPI() {

    }
    private boolean compareData() {//compare the api information and with database useing jdbc
        return true;
    }
    private void addData() { //add to our Database ONLY after we compare
    }
}
