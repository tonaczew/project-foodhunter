package com.maremare.foodhunter.service;

import com.maremare.foodhunter.service.store.HemkopScraper;
import com.maremare.foodhunter.service.store.IcaScraper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class WebScraper {

    private final IcaScraper icaScraper;
    private final HemkopScraper hemkopScraper;

    public WebScraper(IcaScraper icaScraper, HemkopScraper hemkopScraper) {
        this.icaScraper = icaScraper;
        this.hemkopScraper = hemkopScraper;
    }

    public Map<String, String> webScrapingIca(List<String> products) {
        return icaScraper.webScrapeIca(products);
    }

    public Map<String, String> webScrapingHemkop(List<String> products) {
        return hemkopScraper.webScrapeHemkop(products);
    }
}
