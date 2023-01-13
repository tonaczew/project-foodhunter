package com.maremare.foodhunter.Webscraper;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.IOException;

public class WebScraper {

    public String webScrapePage(){
        String content = "";

        try(final WebClient webClient = new WebClient()){
            webClient.getOptions().setCssEnabled(false);
            webClient.getOptions().setJavaScriptEnabled(false);
            webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
            webClient.getOptions().setThrowExceptionOnScriptError(false);
            webClient.getOptions().setPrintContentOnFailingStatusCode(false);
            final HtmlPage page = webClient.getPage("https://www.koket.se/spanska-blamusslor-med-chorizo");

            return page.getTitleText();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }
}
