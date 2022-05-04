package com.example.exchangeratebot.service;

import com.example.exchangeratebot.enums.CurrencyBase;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Service
public class MainMenuService {

    public SendMessage getMainMenuMessage(String chatId, String textMessage) {

        SendMessage mainMenuMessage = new SendMessage(chatId, textMessage);
        mainMenuMessage.setReplyMarkup(getMainMenuKeyboard());

        return mainMenuMessage;
    }

    public InlineKeyboardMarkup getMainMenuKeyboard() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton usdButton = new InlineKeyboardButton();
        InlineKeyboardButton eurButton = new InlineKeyboardButton();
        InlineKeyboardButton rubButton = new InlineKeyboardButton();

        usdButton.setText(CurrencyBase.USD.getCurrencyText());
        eurButton.setText(CurrencyBase.EUR.getCurrencyText());
        rubButton.setText(CurrencyBase.RUB.getCurrencyText());

        usdButton.setCallbackData(CurrencyBase.USD.getCurrencyText());
        eurButton.setCallbackData(CurrencyBase.EUR.getCurrencyText());
        rubButton.setCallbackData(CurrencyBase.RUB.getCurrencyText());

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        row1.add(usdButton);

        List<InlineKeyboardButton> row2 = new ArrayList<>();
        row2.add(eurButton);

        List<InlineKeyboardButton> row3 = new ArrayList<>();
        row3.add(rubButton);

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(row1);
        rowList.add(row2);
        rowList.add(row3);


        inlineKeyboardMarkup.setKeyboard(rowList);

        return inlineKeyboardMarkup;
    }
}
