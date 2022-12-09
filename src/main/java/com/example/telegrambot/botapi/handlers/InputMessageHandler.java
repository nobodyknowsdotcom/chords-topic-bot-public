package com.example.telegrambot.botapi.handlers;

import com.example.telegrambot.utils.BotState;
import com.example.telegrambot.utils.MessageType;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface InputMessageHandler {
    SendMessage handle(long chatId, BotState state);
    MessageType getHandlerName();
}
