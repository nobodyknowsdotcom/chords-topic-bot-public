package com.example.telegrambot.service;

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
public class ChordsTopicBot extends SpringWebhookBot {
    private final BotConfig botConfig;
    private final ReplyHandler replyHandler;

    public ChordsTopicBot(SetWebhook setWebhook, BotConfig botConfig, ReplyHandler replyHandler) {
        super(setWebhook);
        this.botConfig = botConfig;
        this.replyHandler = replyHandler;
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        if(update.hasMessage() && update.getMessage().hasText()){
            String messageText = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();

            SendMessage message = replyHandler.handleMessage(messageText, chatId);
            sendMessage(message);
        }
        return null;
    }

    private void sendMessage(SendMessage message){
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
