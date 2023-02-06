package com.maremare.foodhunter.service.store;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HemkopScraper {

    public Map<String, String> webScrapeHemkop(List<String> shoppingList) {
        Map<String, String> responseData = new HashMap<>();

        String filePath = "C:\\Users\\tonac\\Desktop\\chrome-driver\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", filePath);
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        WebDriver driver = new ChromeDriver(chromeOptions);

        for (String product : shoppingList) {
            try {
                driver.get("https://www.hemkop.se/sok?q=" + product);
                String xPathProduct = "//*[@id='__next']/div[1]/div[5]/div/div[2]/div/div[3]/div[1]/div/div[1]/div[2]/div[2]/div/div/a";
                String xPathPrice = "//*[@id=\"__next\"]/div[1]/div[5]/div/div[2]/div/div[3]/div[1]/div/div[1]/div[3]/div/div/h3";

                WebElement article = driver.findElement(By.xpath(xPathProduct));
                WebElement price = driver.findElement(By.xpath(xPathPrice));

                responseData.put(article.getText(), price.getText());

            } catch (Exception NoSuchElementException) {
                System.out.println("EXEPTION NoSuchElementException");
                responseData.put("Ingen sökträff: " + product, "-");
            }
        }
        return responseData;
    }
}
