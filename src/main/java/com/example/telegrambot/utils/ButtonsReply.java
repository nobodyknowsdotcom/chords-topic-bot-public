package com.example.telegrambot.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ButtonsReply {
    START ("Привет, я бот-скидыватель аккордов и текста!" +
                   "Просто попроси топ аккордов и наслаждайся, надеюсь я сделал твой день чуть лучше :)"),
    HELP ("Моя работа - кидать тебе самые популярные разборы песен с сайта AmDm."),
    TOPIC_FOR_TODAY ("Самые популярные разборы песен на сегодня:"),
    TOPIC_FOR_WEEK ("Самые популярные разборы песен за последние 7 дней"),
    TOPIC_FOR_MONTH ("Самые популярные разборы песен за последний месяц"),
    TOPIC_FOR_ALL_TIME ("Самые популярные разборы песен за все время");

    private final String title;
}
