package com.example.AlbertStats;


import com.example.AlbertStats.Scheduler.JsonReading.ReadJsonFilesService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class ReadJsonTest {
    @MockBean
    private ReadJsonFilesService readJsonFilesService;

    @Test
    public void doesJsonExists() {

    }
}
