package com.example.telegrambot.controller;

import com.example.telegrambot.service.KirRealBot;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@RestController
@AllArgsConstructor
public class KirRealController {
    private final KirRealBot bot;

    @PostMapping("/")
    private BotApiMethod<?> onUpdateReceived(@RequestBody Update update){
        return bot.onWebhookUpdateReceived(update);
    }
}
