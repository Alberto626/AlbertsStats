package com.example.AlbertStats.Scheduler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;


@Configuration
@ComponentScan("com.example.AlbertStats.Scheduler")
@EnableScheduling
public class SchedulerConfig {

    @Bean
    public WebClient webClient() { //this just temporary until i find the write library to call the public api,
        return WebClient
                .builder()
                .build();
    }
    @Bean
    public RestTemplate restTemplate() {//basic idea
        return new RestTemplate();
    }
}
