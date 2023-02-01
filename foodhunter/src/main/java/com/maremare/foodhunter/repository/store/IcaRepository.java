package com.maremare.foodhunter.repository.store;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import com.maremare.foodhunter.Article;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class IcaRepository {

    final String url = "https://handlaprivatkund.ica.se/stores/1003418/";

    public List<Article> webScrapeIca(List<String> shoppingList) {
        List<Article> articles = new ArrayList<>();
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
                articles.add(new Article(productContent.getTextContent(), cleanedPrice));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return articles;
    }

    private double convertPrice(String textContent) {
        String cleanPrice = textContent.replaceAll("[^,0-9]", "").replace(",", ".");
        return Double.parseDouble(cleanPrice);
    }
}
