package com.example.AlbertStats;


import com.example.AlbertStats.Scheduler.JsonReading.JsonFilesToListService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class ReadJsonTest {
    @MockBean
    private JsonFilesToListService readJsonFilesService;

    @Test
    public void doesJsonExists() {

    }
}
