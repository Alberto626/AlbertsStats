package com.example.AlbertStats.Controllers.RestControllers;

import com.example.AlbertStats.DTOs.PlayerDetailsDTO;
import com.example.AlbertStats.Repository.Dota2Repo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DetailsApi {//for now this is the base idea of what is going on
    private final Dota2Repo dota2Repo;

    public DetailsApi(Dota2Repo dota2Repo) {
        this.dota2Repo = dota2Repo;
    }

    @GetMapping("/bankai/{page}")
    public List<PlayerDetailsDTO> world(@PathVariable("page") int page) {
        List<PlayerDetailsDTO> list = dota2Repo.getAllMatchDetails(page);
        return list;
    }
}
