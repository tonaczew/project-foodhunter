package com.maremare.foodhunter.repository;

import com.maremare.foodhunter.Article;
import com.maremare.foodhunter.repository.store.IcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ScrapeRepository {


    final IcaRepository icaRepository;

    @Autowired
    public ScrapeRepository(IcaRepository icaRepository) {
        this.icaRepository = icaRepository;
    }

    public List<Article> webscraping(List<String> products) {
        return icaRepository.webScrapeIca(products);
    }
}
