package com.maremare.foodhunter.service;

import com.maremare.foodhunter.Article;
import com.maremare.foodhunter.ShoppingList;
import com.maremare.foodhunter.repository.ScrapeRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScrapeService {

    final ScrapeRepository scrapeRepository;

    @Autowired
    public ScrapeService(ScrapeRepository scrapeRepository) {
        this.scrapeRepository = scrapeRepository;
    }

    public JSONArray getProducts(List<String> products) {
        List<Article> article = scrapeRepository.webscraping(products);
        ShoppingList shopList = new ShoppingList("Ica", article);
        return createJsonObject(shopList);
    }

    private JSONArray createJsonObject(ShoppingList sl) {
        JSONArray ja = new JSONArray();
        JSONObject jo = new JSONObject();
        jo.put("store", sl.getStore());
        jo.put("shoppingList", sl.getShoppingList());
        ja.put(jo);
        System.out.println(ja);
        return ja;
    }
}
