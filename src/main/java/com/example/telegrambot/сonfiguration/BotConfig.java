package com.example.telegrambot.сonfiguration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class BotConfig {
    @Value("${bot.webhook-path}")
    String webhookPath;
    @Value("${bot.token}")
    private String token;
    @Value("${bot.name}")
    private String name;
    @Value("${bot.topic-page-capability}")
    private String pageCapability;
}
