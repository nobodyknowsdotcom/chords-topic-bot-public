package com.example.telegrambot.botapi.handlers;

import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

public interface QueryHandler {
    EditMessageReplyMarkup handle(CallbackQuery callbackQuery);
}
