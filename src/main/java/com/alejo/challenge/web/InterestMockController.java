package com.alejo.challenge.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/interest")
public class InterestMockController {
    @GetMapping
    public Double calculateInterest () {
        return 10.0;
    }
}
