package com.maremare.foodhunter.service.store;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HemkopScraper {

    private static final String FILE_PATH = "C:\\Users\\tonac\\Desktop\\chrome-driver\\chromedriver.exe";

    public Map<String, String> webScrapeHemkop(List<String> shoppingList) {
        Map<String, String> responseData = new HashMap<>();
        System.setProperty("webdriver.chrome.driver", FILE_PATH);
        var chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        var driver = new ChromeDriver(chromeOptions);

        for (String product : shoppingList) {
            try {
                driver.get("https://www.hemkop.se/sok?q=" + product);
                var xPathProduct = "//*[@id='__next']/div[1]/div[5]/div/div[2]/div/div[3]/div[1]/div/div[1]/div[2]/div[2]/div/div/a";
                var xPathPrice = "//*[@id=\"__next\"]/div[1]/div[5]/div/div[2]/div/div[3]/div[1]/div/div[1]/div[3]/div/div/h3";

                var article = driver.findElement(By.xpath(xPathProduct));
                var price = driver.findElement(By.xpath(xPathPrice));

                responseData.put(article.getText(), price.getText());

            } catch (Exception noSuchElementException) {
                responseData.put("Ingen sökträff: " + product, "-");
            }
        }
        return responseData;
    }
}
