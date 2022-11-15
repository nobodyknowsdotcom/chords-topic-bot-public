package com.example.telegrambot.сonfiguration;

import com.example.telegrambot.service.ChordsTopicBot;
import com.example.telegrambot.service.ReplyHandler;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;

@Configuration
@AllArgsConstructor
public class WebhookConfig {
    private final BotConfig telegramConfig;

    @Bean
    public SetWebhook setWebhookInstance() {
        return SetWebhook.builder().url(telegramConfig.getWebhookPath()).build();
    }

    @Bean
    @Primary
    public ChordsTopicBot springWebhookBot(SetWebhook setWebhook, BotConfig botConfig, ReplyHandler replyHandler) {
        return new ChordsTopicBot(setWebhook, botConfig, replyHandler);
    }
}
