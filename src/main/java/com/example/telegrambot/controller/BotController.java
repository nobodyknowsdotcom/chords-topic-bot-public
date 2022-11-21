package com.example.telegrambot.controller;

import com.example.telegrambot.service.ChordsTopicBot;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@RestController
@AllArgsConstructor
public class BotController {
    private final ChordsTopicBot bot;

    @PostMapping("/")
    private BotApiMethod<?> onUpdateReceived(@RequestBody Update update){
        return bot.onWebhookUpdateReceived(update);
    }
}
