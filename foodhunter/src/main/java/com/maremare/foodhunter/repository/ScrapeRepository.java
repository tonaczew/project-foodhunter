package com.maremare.foodhunter.repository;

import com.maremare.foodhunter.Article;
import com.maremare.foodhunter.repository.store.HemkopRepository;
import com.maremare.foodhunter.repository.store.IcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ScrapeRepository {


    final IcaRepository icaRepository;
    final HemkopRepository hemkopRepository;

    @Autowired
    public ScrapeRepository(IcaRepository icaRepository, HemkopRepository hemkopRepository) {
        this.icaRepository = icaRepository;
        this.hemkopRepository = hemkopRepository;
    }

    public List<Article> webScrapingIca(List<String> products) {
        return icaRepository.webScrapeIca(products);
    }
    public List<Article> webScrapingHemkop(List<String> products) {
        return hemkopRepository.webScrapeHemkop(products);
    }
}
