package com.example.openactiveconsumerandapi.routes;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ping")
public class Ping {

    Ping() {}

    @GetMapping
    public String ping() {
        return "ping";
    }
}
