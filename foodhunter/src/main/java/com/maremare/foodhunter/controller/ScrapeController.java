package com.maremare.foodhunter.controller;

import com.maremare.foodhunter.service.ScrapeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://127.0.0.1:8080", maxAge = 3600)
@RestController
public class ScrapeController {

    private final ScrapeService scrapeService;

    public ScrapeController(ScrapeService scrapeService) {
        this.scrapeService = scrapeService;
    }

    @GetMapping("/getPrice")
    public ResponseEntity<String> getPrice(@RequestParam List<String> products) {
        var sendToFrontend = scrapeService.getProducts(products);
        return new ResponseEntity<>(sendToFrontend.toString(), HttpStatus.OK);
    }
}
