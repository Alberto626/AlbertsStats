package com.example.AlbertStats.Scheduler;

import com.example.AlbertStats.Repository.Dota2Repo;
import com.example.AlbertStats.Services.DotaApiReadingService;
import com.example.AlbertStats.Services.DotaDataComparisonService;
import jakarta.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.Stack;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;


@Component //TODO this might be a service
public class Dota2ApiScheduler {//Basic concept, still not finished

    private final DotaDataComparisonService dotaDataComparisonService;
    private final DotaApiReadingService dotaApiReadingService;
    private final Logger logger = Logger.getLogger(Dota2ApiScheduler.class.getName());

    public Dota2ApiScheduler(DotaDataComparisonService dotaDataComparisonService,
                             DotaApiReadingService dotaApiReadingService) {//

        this.dotaDataComparisonService = dotaDataComparisonService;
        this.dotaApiReadingService = dotaApiReadingService;
    }

    public void updateHeroesToDatabase() {//update heroes to our database every once in a while
        dotaDataComparisonService.updateHeroes();
    }

    public void updateItemsToDatabase() {//Read from my json file and update to the mysql database
        dotaDataComparisonService.updateItems();
    }
    public void updateMatchDetails() {
        Stack<BigInteger> stack = new Stack<>();
        stack.addAll(dotaApiReadingService.getAllMatchesIds());

        while(stack.size() != 100) {//TODO apparently alot of matches from opendota dont have the matches, we need to find alternate solution
            stack.pop();
        }
        while(!stack.isEmpty()) {
            BigInteger matchId = stack.pop();
            if(!dotaDataComparisonService.doesMatchRecordExist(matchId)) { //if the match records exists, then we dont need to make the http request, if it does then make the call and add to our database
                dotaApiReadingService.readAndWriteMatchDetails(matchId);//add to match and player details, this will ignore matches will give an error like 404
            }
            try {
                TimeUnit.SECONDS.sleep(10);// wait at least 10 seconds because of the limitations of opendota api
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
        }
    }



}
