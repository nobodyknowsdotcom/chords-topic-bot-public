package com.example.telegrambot.controller;

import com.example.telegrambot.botapi.SongsTopicBot;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@RestController
@AllArgsConstructor
public class BotController {
    private final SongsTopicBot songsTopicBot;

    @PostMapping("/")
    public BotApiMethod<?> onUpdateReceived(@RequestBody Update update){
        return songsTopicBot.onWebhookUpdateReceived(update);
    }
}
