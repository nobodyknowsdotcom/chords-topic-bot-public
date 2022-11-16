package com.example.telegrambot.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ButtonsText {
    HELP ("Что делает этот бот? \uD83E\uDD14"),
    TOPIC_FOR_TODAY ("Получить топ разборов песен за сегодня"),
    TOPIC_FOR_WEEK ("Получить топ разборов песен за неделю"),
    TOPIC_FOR_MONTH ("Получить топ разборов песен за месяц"),
    TOPIC_FOR_ALL_TIME ("Получить топ разборов песен за все время");

    private String title;
}
