package com.example.AlbertStats.Scheduler.JsonReading;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class ReadJsonFilesService {
    @Value("classpath:data/heros.json")
    private Resource heros;
    @Value("classpath:data/items.json")
    private Resource items;

}
