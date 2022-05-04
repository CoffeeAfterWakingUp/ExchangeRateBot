package com.example.exchangeratebot.botapi;

import com.example.exchangeratebot.enums.CurrencyBase;
import com.example.exchangeratebot.model.HistoricalRate;
import com.example.exchangeratebot.service.ExchangeRateHistoryService;
import com.example.exchangeratebot.service.MainMenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class Handler {

    private final MainMenuService mainMenuService;
    private final ExchangeRateHistoryService exchangeRateHistoryService;

    private static final String WELCOME_MESSAGE = "" +
            "Hello %s! \n" +
            "\n" +
            "This bot helps you to obtain a history of changes " +
            "in the exchange rate (USD, EUR, RUB) against tenge for 10 days. \n" +
            "\n" +
            "Specail thanks to https://exchangeratesapi.io/ !" +
            "\n" +
            "Bot was done by https://t.me/olzhassyrbek";


    public BotApiMethod<?> handleUpdate(Update update) {
        SendMessage replyMessage = null;

        if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            log.info("New callbackQuery from User: {}, userId: {}, with data: {}", update.getCallbackQuery().getFrom().getUserName(),
                    callbackQuery.getFrom().getId(), update.getCallbackQuery().getData());
            return processCallbackQuery(callbackQuery);
        }

        Message message = update.getMessage();
        if (message != null && message.hasText()) {
            log.info("New message from User:{}, chatId: {},  with text: {}",
                    message.getFrom().getUserName(), message.getChatId(), message.getText());
            replyMessage = handleInputMessage(message);
        }

        return replyMessage;
    }

    private SendMessage handleInputMessage(Message message) {
        String chatId = message.getChatId().toString();
        String firstName = Optional.ofNullable(message.getFrom().getFirstName()).orElse("");
        String lastName = Optional.ofNullable(message.getFrom().getLastName()).orElse("");
        String fullName = firstName + " " + lastName;
        String messageText = String.format(WELCOME_MESSAGE, fullName.trim());
        SendMessage replyMessage = mainMenuService.getMainMenuMessage(chatId, messageText);
        return replyMessage;
    }

    private BotApiMethod<?> processCallbackQuery(CallbackQuery buttonQuery) {
        String chatId = buttonQuery.getMessage().getChatId().toString();
        LocalDate now = LocalDate.now();
        LocalDate tenDaysBefore = LocalDate.now().minusDays(10L);
        String text = "Please choose currency!";
        SendMessage callBackAnswer = mainMenuService.getMainMenuMessage(chatId, text);
        String currency = buttonQuery.getData();

        if (!CurrencyBase.currencies().contains(currency)) {
            currency = CurrencyBase.EUR.getCurrencyText();
        }
        HistoricalRate historicalRate = exchangeRateHistoryService.getHistoricalRatesToTenge(tenDaysBefore, now, currency);
        if (!Optional.ofNullable(historicalRate).isPresent()) {
            return callBackAnswer;
        }

        text = constructResponseText(historicalRate);
        callBackAnswer = mainMenuService.getMainMenuMessage(chatId, text);


        return callBackAnswer;

    }


    private String constructResponseText(HistoricalRate historicalRate) {
        StringBuilder sb = new StringBuilder();
        Map<String, Map<String, Double>> rates = historicalRate.getRates();
        String startDate = historicalRate.getStartDate();
        String endDate = historicalRate.getEndDate();
        String base = historicalRate.getBase();

        sb.append("Start Date: ").append(startDate).append("\n");
        sb.append("End Date: ").append(endDate).append("\n");
        sb.append("Currency Base: ").append(base).append("\n");
        sb.append("\n");
        rates.forEach((key, value) -> {
            sb.append(key).append(":").append("\n");
            value.forEach((key1, value1) -> sb.append(key1).append(": ").append(value1).append("\n"));
            sb.append("\n");
        });
        return sb.toString();
    }
}
