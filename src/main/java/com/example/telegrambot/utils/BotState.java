package com.example.telegrambot.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BotState {
    TODAY,
    WEEK,
    MONTH,
    ALL,
    HELP,
    START,
    ERROR

}
