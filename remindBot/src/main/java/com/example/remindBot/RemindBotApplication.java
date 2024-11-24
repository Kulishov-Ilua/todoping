package com.example.remindBot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.LongPollingBot;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@SpringBootApplication
@EnableJpaRepositories
@EntityScan
@EnableScheduling
public class RemindBotApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx=SpringApplication.run(RemindBotApplication.class,args);
		try{
			TelegramBotsApi telegramBotsApi=new TelegramBotsApi(DefaultBotSession.class);
			telegramBotsApi.registerBot(ctx.getBean("reminderBot", LongPollingBot.class));
		} catch (TelegramApiException e) {
			throw new RuntimeException(e);
		}

	}
}
