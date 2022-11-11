package com.example.telegrambot.service;

import com.example.telegrambot.сonfiguration.BotConfig;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.starter.SpringWebhookBot;

@Slf4j
@Getter
@Setter
@Service
@Component
public class KirRealBot extends SpringWebhookBot {

    BotConfig botConfig;
    MessageProcessing messageProcessing;

    public KirRealBot(SetWebhook setWebhook, BotConfig botConfig) {
        super(setWebhook);
        this.botConfig = botConfig;
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
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        if(update.hasMessage() && update.getMessage().hasText()){
            String messageText = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();

            sendMessage(messageText, chatId);
        }
        return null;
    }

    private void sendMessage(String text, Long chatId){
        SendMessage message = MessageProcessing.getEchoMessage(text, chatId);

        try{
            execute(message);
        }
        catch (TelegramApiException e){
            log.error(String.format("Can't send message '%s'!",text));
        }
    }

    @Override
    public String getBotPath() {
        return null;
    }
}
