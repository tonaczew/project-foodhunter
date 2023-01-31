package com.maremare.foodhunter;

public class Article {

    private final String productName;
    private final double price;

    public Article(String productName, double price) {
        this.productName = productName;
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Article{" +
                "productName='" + productName + '\'' +
                ", price=" + price +
                '}';
    }
}
