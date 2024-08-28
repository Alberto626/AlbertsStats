package com.example.AlbertStats.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Dota2MainController {

    @GetMapping("Dota")
    public String get(Model model) {
        model.addAttribute("This", null);
        return "";
    }
}
