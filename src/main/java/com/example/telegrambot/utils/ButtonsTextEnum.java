package com.example.telegrambot.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ButtonsTextEnum {
    HELP ("Что делает этот бот? \uD83E\uDD14"),
    TOPIC_FOR_TODAY ("Самые популярные разборы песен за последние 24 часа \uD83C\uDFB8"),
    TOPIC_FOR_WEEK ("Самые популярные разборы песен за последние 7 дней \uD83C\uDFB8"),
    TOPIC_FOR_MONTH ("Самые популярные разборы песен за последний месяц \uD83C\uDFB8"),
    TOPIC_FOR_ALL_TIME ("Самые популярные разборы песен за все время \uD83C\uDFB8");

    private String title;
}
