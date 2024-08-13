package com.uniTrade.uniTrade;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class UniTradeApplication {

	public static void main(String[] args) {

		Dotenv dotenv = Dotenv.load();
		// Set system properties from .env file
		System.setProperty("SPRING_DATA_MONGODB_URI", dotenv.get("SPRING_DATA_MONGODB_URI"));
		System.setProperty("SPRING_DATA_MONGODB_DATABASE", dotenv.get("SPRING_DATA_MONGODB_DATABASE"));


		SpringApplication.run(UniTradeApplication.class, args);
	}

}
