package com.hexagon.bookhub.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class HomeController {

    @GetMapping("/")
    public String homePage() {
        return "BookHub UOC Backend Service";
    }

}
