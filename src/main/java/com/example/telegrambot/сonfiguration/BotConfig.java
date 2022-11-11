package com.example.telegrambot.сonfiguration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class BotConfig {
    @Value("${telegram.webhook-path}")
    String webhookPath;
    @Value("${bot.token}")
    private String token;
    @Value("${bot.name}")
    private String name;
}
