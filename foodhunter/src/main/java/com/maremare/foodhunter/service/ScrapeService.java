package com.maremare.foodhunter.service;

import com.maremare.foodhunter.Article;
import com.maremare.foodhunter.ShoppingList;
import com.maremare.foodhunter.repository.ScrapeRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ScrapeService {
    enum Store {
        ICA, HEMKÖP
    }

    final ScrapeRepository scrapeRepository;

    @Autowired
    public ScrapeService(ScrapeRepository scrapeRepository) {
        this.scrapeRepository = scrapeRepository;
    }

    public JSONArray getProducts(List<String> products) {
        List<ShoppingList> shopList = new ArrayList<>();
        Map<String, String> articleIca;
        Map<String, String> articleHemkop;

        for (Store store : Store.values()) {
            switch (store.name()) {
                case "ICA" -> {
                    articleIca = scrapeRepository.webScrapingIca(products);
                    List<Article> articleList = createArticleList(store.name(), articleIca);
                    shopList.add(new ShoppingList(store.name(), articleList));
                }
                case "HEMKÖP" -> {
                    articleHemkop = scrapeRepository.webScrapingHemkop(products);
                    List<Article> articleList = createArticleList(store.name(), articleHemkop);
                    shopList.add(new ShoppingList(store.name(), articleList));
                }
            }
        }
        return createJsonObject(shopList);
    }

    private List<Article> createArticleList(String name, Map<String, String> articleIca) {
        List<Article> articleList = new ArrayList<>();
        articleIca.forEach((k,v) -> {
            articleList.add(new Article(k,convertPrice(v)));
        });
        return articleList;
    }

    private JSONArray createJsonObject(List<ShoppingList> sl) {
        JSONArray jsonArray = new JSONArray();
        for (ShoppingList storeList : sl) {
            JSONObject jsonArticleObject = new JSONObject();
            jsonArticleObject.put("store", storeList.getStore());
            jsonArticleObject.put("shoppingList", storeList.getShoppingList());
            jsonArray.put(jsonArticleObject);
        }
        System.out.println(jsonArray);
        return jsonArray;
    }

    private double convertPrice(String textContent) {
        String cleanPrice = textContent.replaceAll("[^,0-9]", "").replace(",", ".");
        return Double.parseDouble(cleanPrice);
    }
}
