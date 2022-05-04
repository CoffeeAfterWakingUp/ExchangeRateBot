package com.example.exchangeratebot.botapi;

import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

public class ExchangeRateBot extends TelegramWebhookBot {

    private String webHookPath;
    private String botUsername;
    private String botToken;

    private final Handler handler;

    public ExchangeRateBot(Handler handler) {
        this.handler = handler;
    }

    public ExchangeRateBot(DefaultBotOptions options, Handler handler) {
        super(options);
        this.handler = handler;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        return handler.handleUpdate(update);
    }

    @Override
    public String getBotPath() {
        return webHookPath;
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    public void setWebHookPath(String webHookPath) {
        this.webHookPath = webHookPath;
    }

    public void setBotUsername(String botUsername) {
        this.botUsername = botUsername;
    }

    public void setBotToken(String botToken) {
        this.botToken = botToken;
    }
}
