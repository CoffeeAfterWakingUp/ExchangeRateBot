package com.example.exchangeratebot.controller;

import com.example.exchangeratebot.botapi.ExchangeRateBot;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@RestController
public class WebHookController {

    private final ExchangeRateBot exchangeRateBot;

    public WebHookController(ExchangeRateBot exchangeRateBot) {
        this.exchangeRateBot = exchangeRateBot;
    }

    @PostMapping
    public BotApiMethod<?> onUpdateReceived(@RequestBody Update update) {
        return exchangeRateBot.onWebhookUpdateReceived(update);
    }
}
