package com.maremare.foodhunter.service;

import com.maremare.foodhunter.Article;
import com.maremare.foodhunter.ShoppingList;
import com.maremare.foodhunter.Webscraper.IcaScraper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScrapeService {

    IcaScraper ica = new IcaScraper();


    public JSONArray getProducts(List<String> products) {
        List<String> sl = new ArrayList<>(products);
        List<Article> article = ica.scrapeContent(sl);
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
