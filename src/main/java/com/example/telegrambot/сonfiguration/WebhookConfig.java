package com.example.telegrambot.сonfiguration;

import com.example.telegrambot.service.KirRealBot;
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
    public KirRealBot springWebhookBot(SetWebhook setWebhook, BotConfig botConfig) {
        return new KirRealBot(setWebhook, botConfig);
    }
}
