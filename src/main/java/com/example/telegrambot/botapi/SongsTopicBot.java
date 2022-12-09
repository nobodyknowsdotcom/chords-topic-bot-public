package com.example.telegrambot.botapi;

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
public class SongsTopicBot extends SpringWebhookBot {
    private final BotConfig botConfig;
    private final TelegramFacade telegramFacade;

    public SongsTopicBot(SetWebhook setWebhook, BotConfig botConfig, TelegramFacade telegramFacade) {
        super(setWebhook);
        this.botConfig = botConfig;
        this.telegramFacade = telegramFacade;
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        return telegramFacade.handleUpdate(update);
    }

    private void sendMessage(SendMessage message){
        try {
            log.info(String.format("Sent message '%s...' to '%s'",
                    message.getText().substring(0, 10), message.getChatId()));
            execute(message);
        }
        catch (TelegramApiException e){
            log.error(String.format("Can't send message '%s' to '%s'!",
                    message.getText().substring(0, 10), message.getChatId()));
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
