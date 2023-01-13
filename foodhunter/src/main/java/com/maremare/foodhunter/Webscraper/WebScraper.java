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
            displayNameField.type("kvarg");
            HtmlPage resultsPage = submitButton.click();

            HtmlHeading1 h1 = (HtmlHeading1) resultsPage.getByXPath("//main/div/div/ul/li/h1").get(0);

            System.out.println(h1.getTextContent());


            return page.getTitleText();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }
}
