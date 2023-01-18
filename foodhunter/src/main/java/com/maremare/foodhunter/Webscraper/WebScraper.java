package com.maremare.foodhunter.Webscraper;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;

import java.io.IOException;

public class WebScraper {

    public String webScrapePage(){
        String content = "";

        try(final WebClient webClient = new WebClient()){
            webClient.getOptions().setCssEnabled(false);
            webClient.getOptions().setJavaScriptEnabled(false);
            String url = "https://handlaprivatkund.ica.se/stores/1003418/";
            final HtmlPage page = webClient.getPage(url);
            HtmlForm form = (HtmlForm) page.getByXPath("//form").get(0);
            HtmlInput displayNameField = form.getInputByName("q");
            HtmlButton submitButton = (HtmlButton) form.getByXPath("//button").get(0);
            displayNameField.type("mellanmjölk 1l ica");
            HtmlPage resultsPage = submitButton.click();


            HtmlSpan spanResult = (HtmlSpan) resultsPage.getByXPath("//span[starts-with(@class,'text__Text-sc-6l1yjp-0 jdRmmJ')]").get(0);
            HtmlHeading1 h1 = (HtmlHeading1) resultsPage.getByXPath("//h1[starts-with(@class,'heading__Base-sc-1vuwqc7-0-h1')]").get(0);

            System.out.println(spanResult.getTextContent() + ": " + h1.getTextContent());


            return page.getTitleText();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }
}
