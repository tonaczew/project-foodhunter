package com.maremare.foodhunter.model;

public class Article {

    private final String productName;
    private final String price;

    public Article(String productName, String price) {
        this.productName = productName;
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public String getPrice() {
        return price;
    }
}
