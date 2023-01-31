package com.maremare.foodhunter;

import org.json.JSONObject;

import java.util.List;

public class ShoppingList {

    private String store;
    private List<Article> shoppingList;

    public ShoppingList() {
    }

    public ShoppingList(String store, List<Article> shoppingList) {
        this.store = store;
        this.shoppingList = shoppingList;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public List<Article> getShoppingList() {
        return shoppingList;
    }

    public void setShoppingList(List<Article> shoppingList) {
        this.shoppingList = shoppingList;
    }
}
