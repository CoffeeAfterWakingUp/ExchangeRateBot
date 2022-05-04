package com.example.exchangeratebot.config;

import com.example.exchangeratebot.botapi.ExchangeRateBot;
import com.example.exchangeratebot.botapi.Handler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BotConfig {

    @Value("${telegrambot.webHookPath}")
    private String webHookPath;

    @Value("${telegrambot.username}")
    private String botUsername;

    @Value("${telegrambot.botToken}")
    private String botToken;

    @Bean
    public ExchangeRateBot exchangeRateTelegramBot(Handler handler) {

        ExchangeRateBot exchangeRateBot = new ExchangeRateBot(handler);
        exchangeRateBot.setBotUsername(botUsername);
        exchangeRateBot.setBotToken(botToken);
        exchangeRateBot.setWebHookPath(webHookPath);

        return exchangeRateBot;
    }

}
