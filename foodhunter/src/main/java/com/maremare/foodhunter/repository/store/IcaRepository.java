package com.maremare.foodhunter.repository.store;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import com.maremare.foodhunter.Article;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class IcaRepository {

    final String url = "https://handlaprivatkund.ica.se/stores/1003418/";

    public Map<String, String> webScrapeIca(List<String> shoppingList) {
        Map<String, String> responseData = new HashMap<>();
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
                try{
                    HtmlAnchor productContent = (HtmlAnchor) resultPge.getByXPath("//a[starts-with(@data-test,'fop-product-link')]").get(0);
                    HtmlStrong productPrice = (HtmlStrong) resultPge.getByXPath("//strong[starts-with(@data-test,'fop-price')]").get(0);
                    responseData.put(productContent.getTextContent(),productPrice.getTextContent());

                } catch(Exception IndexOutOfBoundsException){
                    System.out.println("EXEPTION IndexOutOfBoundsException");
                    responseData.put("Ingen sökträff: " + product, "-");
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseData;
    }

}
