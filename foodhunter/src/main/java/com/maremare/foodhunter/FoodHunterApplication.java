package com.maremare.foodhunter;

import com.maremare.foodhunter.Webscraper.WebScraper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FoodHunterApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodHunterApplication.class, args);

		WebScraper ws = new WebScraper();

		System.out.println(ws.webScrapePage());
	}

}
