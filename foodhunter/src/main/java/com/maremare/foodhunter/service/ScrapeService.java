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

@Service
public class ScrapeService {
    enum Store {
        ICA
    }

    final ScrapeRepository scrapeRepository;

    @Autowired
    public ScrapeService(ScrapeRepository scrapeRepository) {
        this.scrapeRepository = scrapeRepository;
    }

    public JSONArray getProducts(List<String> products) {
        List<ShoppingList> shopList = new ArrayList<>();
        for (Store store : Store.values()) {
            List<Article> article = scrapeRepository.webScrapingIca(products);
            shopList.add(new ShoppingList(store.name(), article));
        }
        return createJsonObject(shopList);
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
}
