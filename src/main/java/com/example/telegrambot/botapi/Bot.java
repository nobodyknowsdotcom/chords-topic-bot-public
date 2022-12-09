package com.example.telegrambot.botapi;

import com.example.telegrambot.botapi.handlers.TelegramFacade;
import com.example.telegrambot.сonfiguration.BotConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.starter.SpringWebhookBot;

@Slf4j
@Service
public class Bot extends SpringWebhookBot {
    private final BotConfig botConfig;
    private final TelegramFacade telegramFacade;

    public Bot(SetWebhook setWebhook, BotConfig botConfig, TelegramFacade telegramFacade) {
        super(setWebhook);
        this.botConfig = botConfig;
        this.telegramFacade = telegramFacade;
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        if(update.hasMessage() && update.getMessage().hasText()){
            SendMessage message = telegramFacade.handleInputMessage(update.getMessage());
            send(message);
        }
        return null;
    }

    private void send(SendMessage message){
        try{
            execute(message);
        }
        catch (TelegramApiException e){
            log.error(String.format("Can't send message '%s' to '%s'!", message.getText(), message.getChatId()));
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return botConfig.getName();
    }

    @Override
    public String getBotToken() {
        return botConfig.getToken();
    }

    @Override
    public String getBotPath() {
        return botConfig.getWebhookPath();
    }
}
