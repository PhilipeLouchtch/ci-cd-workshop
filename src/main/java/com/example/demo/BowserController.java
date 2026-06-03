package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bowser")
public class BowserController {

    @GetMapping
    public String bowser() {
        return "Bowser roars!";
    }
}
