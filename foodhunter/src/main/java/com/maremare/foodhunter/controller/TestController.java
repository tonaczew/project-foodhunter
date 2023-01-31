package com.maremare.foodhunter.controller;

import com.maremare.foodhunter.Article;
import com.maremare.foodhunter.ShoppingList;
import com.maremare.foodhunter.Webscraper.IcaScraper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@CrossOrigin(origins = "http://127.0.0.1:8080", maxAge = 3600)
@RestController
public class TestController {

    IcaScraper ica = new IcaScraper();

    @GetMapping("/list2")
    public ResponseEntity<String> listTest2(@RequestParam List<String> id){
        ShoppingList sl = createShoppingListStore(id);
        JSONArray sendToFrontend = createJsonObject(sl);
        System.out.println(sl.getShoppingList().size());
        return new ResponseEntity<>(sendToFrontend.toString(), HttpStatus.OK);
    }

    @GetMapping("/ica")
    public ResponseEntity<String> icaProducts(@RequestParam List<String> id){
        List<String> sl = new ArrayList<>(id);
        List<Article> article = ica.scrapeContent(sl);
        ShoppingList shopList = new ShoppingList("Ica", article);
        JSONArray sendToFrontend = createJsonObject(shopList);

        return new ResponseEntity<>(sendToFrontend.toString(), HttpStatus.OK);
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

    private void printShoppingList(ShoppingList sl) {
        System.out.println(sl.getStore());
        System.out.println(sl.getShoppingList().toString());
    }

    private ShoppingList createShoppingListStore(List<String> id) {
        List<Article> shoppingList = new ArrayList<>();
        for(String str : id){
            shoppingList.add(new Article(str, 19));
        }
        return new ShoppingList("Ica", shoppingList);
    }
}
