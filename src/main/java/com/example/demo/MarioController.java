package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mario")
public class MarioController {

    @GetMapping
    public String mario() {
        return "It's-a me, Mario!";
    }
}
