package com.example.telegrambot.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ButtonsText {
    HELP ("Что делает этот бот? \uD83E\uDD14"),
    TOPIC_FOR_TODAY ("Хочу топ разборов песен за сегодня \uD83E\uDD20"),
    TOPIC_FOR_WEEK ("Хочу топ разборов песен за неделю ☺️"),
    TOPIC_FOR_MONTH ("Хочу топ разборов песен за месяц \uD83E\uDD78"),
    TOPIC_FOR_ALL_TIME ("Хочу топ разборов песен за все время \uD83E\uDD79");

    private String title;
}
