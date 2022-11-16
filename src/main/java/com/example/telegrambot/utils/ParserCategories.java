package com.example.telegrambot.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ParserCategories {
    TODAY ("today"),
    WEEK ("week"),
    MONTH ("month"),
    ALL ("all");

    private String title;
}
