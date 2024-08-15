package com.example.AlbertStats.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController { //This is gonna be the front page

    @GetMapping("")
    public String get() {
        return "index.html";
    }
}
