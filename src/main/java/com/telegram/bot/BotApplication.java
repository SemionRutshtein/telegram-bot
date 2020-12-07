package com.telegram.bot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

@SpringBootApplication
@EnableDiscoveryClient
public class BotApplication {

	public static void main(String[] args) {

		ApiContextInitializer.init();
		TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
		try {
			telegramBotsApi.registerBot(new EventBot());

		} catch (TelegramApiRequestException e) {
			e.printStackTrace();
		}

		SpringApplication.run(BotApplication.class, args);


	}

}
