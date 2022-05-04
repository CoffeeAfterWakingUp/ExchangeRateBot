package com.example.exchangeratebot.enums;

import java.util.EnumSet;
import java.util.Set;
import java.util.stream.Collectors;

public enum CurrencyBase {
    USD("USD"),
    RUB("RUB"),
    EUR("EUR"),
    KZT("KZT");

    private final String currencyText;

    CurrencyBase(String currencyText) {
        this.currencyText = currencyText;
    }

    private static final Set<String> currencies = EnumSet.allOf(CurrencyBase.class)
            .stream()
            .map(CurrencyBase::getCurrencyText).collect(Collectors.toSet());

    public static Set<String> currencies() {
        return currencies;
    }

    public String getCurrencyText() {
        return currencyText;
    }
}
