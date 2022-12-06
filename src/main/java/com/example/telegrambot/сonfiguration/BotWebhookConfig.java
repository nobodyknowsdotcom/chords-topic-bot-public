package com.example.telegrambot.сonfiguration;

import com.example.telegrambot.botapi.Bot;
import com.example.telegrambot.botapi.TelegramFacade;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;

@Configuration
@AllArgsConstructor
public class BotWebhookConfig {
    private final BotConfig telegramConfig;

    @Bean
    public SetWebhook setWebhookInstance() {
        return SetWebhook.builder().url(telegramConfig.getWebhookPath()).build();
    }

    @Bean
    @Primary
    public Bot springWebhookBot(SetWebhook setWebhook, BotConfig botConfig, TelegramFacade telegramFacade) {
        return new Bot(setWebhook, botConfig, telegramFacade);
    }
}
