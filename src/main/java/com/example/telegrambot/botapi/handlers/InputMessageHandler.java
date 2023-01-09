package com.example.telegrambot.botapi.handlers;

import com.example.telegrambot.enums.BotState;
import com.example.telegrambot.enums.MessageType;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface InputMessageHandler {
    SendMessage handle(long chatId, BotState state);
    MessageType getHandlerName();
}
