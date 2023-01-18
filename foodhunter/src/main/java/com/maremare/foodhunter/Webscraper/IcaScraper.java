package com.maremare.foodhunter.Webscraper;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;

import java.io.IOException;
import java.net.MalformedURLException;

public class IcaScraper {


    public String icaWebScrapePage(){
        final String url = "https://handlaprivatkund.ica.se/stores/1003418/";
        String content = "";

        try(final WebClient webClient = new WebClient()){
            webClient.getOptions().setJavaScriptEnabled(false);
            webClient.getOptions().setCssEnabled(false);
            final HtmlPage page = webClient.getPage(url);
            HtmlForm searchForm = (HtmlForm) page.getByXPath("//form").get(0);
            HtmlInput inputTextField = searchForm.getInputByName("q");
            HtmlButton searchButton = (HtmlButton) searchForm.getByXPath("//button").get(0);
            inputTextField.type("kvarg naturell lindahls 500g");
            HtmlPage resultPge = searchButton.click();

            HtmlAnchor productContent = (HtmlAnchor) resultPge.getByXPath("//a[starts-with(@data-test,'fop-product-link')]").get(0);
            HtmlStrong productPrice = (HtmlStrong) resultPge.getByXPath("//strong[starts-with(@data-test,'fop-price')]").get(0);

            String product = productContent.getTextContent();
            String price = productPrice.getTextContent();

            System.out.printf("Product: %s\n Price: %s", product, price);


        } catch (IOException e) {
            e.printStackTrace();
        }


        return content;
    }


}
