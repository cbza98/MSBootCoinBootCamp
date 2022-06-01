package com.MSBootCoinBootCamp.domain.enums;

public enum CurrencyExchange {
    Soles("PEN"),
    BootCoin("BCN");

    public final String type;

    CurrencyExchange(String type) {
        this.type = type;
    }
}
