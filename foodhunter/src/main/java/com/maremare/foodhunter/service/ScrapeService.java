package com.maremare.foodhunter.service;

import com.maremare.foodhunter.model.Article;
import com.maremare.foodhunter.model.ShoppingList;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ScrapeService {
    private enum Store {
        ICA, HEMKÖP
    }

    private final WebScraper webScraper;

    public ScrapeService(WebScraper webScraper) {
        this.webScraper = webScraper;
    }

    public JSONArray getProducts(List<String> products) {
        List<ShoppingList> shopList = new ArrayList<>();
        Map<String, String> articleIca;
        Map<String, String> articleHemkop;

        for (Store store : Store.values()) {
            if (store.name().equals("ICA")) {
                articleIca = webScraper.webScrapingIca(products);
                var articleList = createArticleList(articleIca);
                shopList.add(new ShoppingList(store.name(), articleList));
            } else if (store.name().equals("HEMKÖP")) {
                articleHemkop = webScraper.webScrapingHemkop(products);
                var articleList = createArticleList(articleHemkop);
                shopList.add(new ShoppingList(store.name(), articleList));
            }
        }
        return createJsonObject(shopList);
    }

    private List<Article> createArticleList(Map<String, String> articleIca) {
        List<Article> articleList = new ArrayList<>();
        articleIca.forEach((k, v) -> articleList.add(new Article(k, convertPrice(v))));
        return articleList;
    }

    private JSONArray createJsonObject(List<ShoppingList> sl) {
        var jsonArray = new JSONArray();
        sl.forEach(storeList -> {
            var jsonArticleObject = new JSONObject();
            jsonArticleObject.put("store", storeList.store());
            jsonArticleObject.put("shoppingList", storeList.shoppingList());
            jsonArray.put(jsonArticleObject);
        });
        return jsonArray;
    }

    private String convertPrice(String textContent) {
        return textContent.equals("-") ? "-" :
                textContent.replaceAll("[^,0-9]", "").replace(",", ".");
    }
}
