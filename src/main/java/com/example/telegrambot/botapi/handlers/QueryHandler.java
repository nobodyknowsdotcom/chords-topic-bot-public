package com.example.telegrambot.botapi.handlers;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

public interface QueryHandler {
    BotApiMethod handle(CallbackQuery callbackQuery);
}
