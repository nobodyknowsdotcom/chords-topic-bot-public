package com.example.telegrambot.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ReplyToUser {
    START ("Привет, я бот-скидыватель самых популярных подборов аккордов с AmDm!" +
                   "\nПросто попроси топ за нужный тебе промежуток времени и наслаждайся :)"),
    HELP ("Моя работа - кидать тебе самые популярные разборы песен с сайта AmDm."),
    ERROR("Не могу обработать это сообщение, т.к. оно пустое."),
    TOPIC_FOR_TODAY ("Самые популярные разборы песен на сегодня:"),
    TOPIC_FOR_WEEK ("Самые популярные разборы песен за последние 7 дней:"),
    TOPIC_FOR_MONTH ("Самые популярные разборы песен за последний месяц:"),
    TOPIC_FOR_ALL_TIME ("Самые популярные разборы песен за все время:");

    private final String title;
}
