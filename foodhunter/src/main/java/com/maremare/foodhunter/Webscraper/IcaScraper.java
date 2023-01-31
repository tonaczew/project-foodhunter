package com.maremare.foodhunter.Webscraper;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import com.maremare.foodhunter.Article;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IcaScraper {

    final String url = "https://handlaprivatkund.ica.se/stores/1003418/";
    final List<Article> articles = new ArrayList<>();
    List<String> shoppingList = new ArrayList<>();


    public String icaWebScrapePage() {
        String content = "";

        //shoppingList = createShoppingList();
        shoppingList.add("kvarg");
        shoppingList.add("tandkräm");
        scrapeContent(shoppingList);

        return content;
    }

    public List<Article> scrapeContent(List<String> shoppingList) {
        double totalAmount = 0.0;
        try (final WebClient webClient = new WebClient()) {
            webClient.getOptions().setJavaScriptEnabled(false);
            webClient.getOptions().setCssEnabled(false);

            for (String product : shoppingList) {
                final HtmlPage page = webClient.getPage(url);
                final HtmlForm searchForm = (HtmlForm) page.getByXPath("//form").get(0);
                final HtmlInput inputTextField = searchForm.getInputByName("q");
                final HtmlButton searchButton = (HtmlButton) searchForm.getByXPath("//button").get(0);
                inputTextField.type(product);

                HtmlPage resultPge = searchButton.click();
                HtmlAnchor productContent = (HtmlAnchor) resultPge.getByXPath("//a[starts-with(@data-test,'fop-product-link')]").get(0);
                HtmlStrong productPrice = (HtmlStrong) resultPge.getByXPath("//strong[starts-with(@data-test,'fop-price')]").get(0);

                double cleanedPrice = convertPrice(productPrice.getTextContent());
                totalAmount += cleanedPrice;
                articles.add(new Article(productContent.getTextContent(), cleanedPrice));
            }

            for (Article a : articles) {
                System.out.printf("Product: %s\n Price: %s\n", a.getProductName(), a.getPrice());
            }

            System.out.println("Total amount: " + totalAmount);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return articles;
    }

    private double convertPrice(String textContent) {
        String cleanPrice = textContent.replaceAll("[^,0-9]", "").replace(",",".");
        return Double.parseDouble(cleanPrice);
    }

    private List<String> createShoppingList() {
        List<String> temp = new ArrayList<>();
        try (Scanner scan = new Scanner(System.in)) {
            String userInput;

            do {
                userInput = scan.nextLine().toLowerCase().trim();
                temp.add(userInput);
            } while (userInput.length() != 0);
        }
        return temp;
    }
}
