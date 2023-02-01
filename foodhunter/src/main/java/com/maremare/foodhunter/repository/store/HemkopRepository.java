package com.maremare.foodhunter.repository.store;

import com.maremare.foodhunter.Article;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class HemkopRepository {

    final String url = "https://www.hemkop.se/sok?q=kvarg";

    public List<Article> webScrapeHemkop(List<String> shoppingList) {
        List<Article> articles = new ArrayList<>();

        String filePath = "C:\\Users\\tonac\\Desktop\\chrome-driver\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", filePath);
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        WebDriver driver = new ChromeDriver(chromeOptions);

        for (String product : shoppingList) {

            driver.get("https://www.hemkop.se/sok?q=" + product);
            String xPathProduct = "//*[@id='__next']/div[1]/div[5]/div/div[2]/div/div[3]/div[1]/div/div[1]/div[2]/div[2]/div/div/a";
            String xPathPrice = "//*[@id=\"__next\"]/div[1]/div[5]/div/div[2]/div/div[3]/div[1]/div/div[1]/div[3]/div/div/h3";

            WebElement article = driver.findElement(By.xpath(xPathProduct));
            WebElement price = driver.findElement(By.xpath(xPathPrice));

            double cleanedPrice = convertPrice(price.getText());
            articles.add(new Article(article.getText(), cleanedPrice));
        }
        return articles;
    }

    private double convertPrice(String textContent) {
        String cleanPrice = textContent.replaceAll("[^,0-9]", "").replace(",", ".");
        return Double.parseDouble(cleanPrice);
    }
}
