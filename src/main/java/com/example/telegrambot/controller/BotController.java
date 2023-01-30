package com.example.telegrambot.controller;

import com.example.telegrambot.botapi.SongsTopicBot;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@RestController
@AllArgsConstructor
@Log4j2
public class BotController {
    private final SongsTopicBot songsTopicBot;

    @GetMapping(value = "/")
    public BotApiMethod onUpdateReceived(@RequestBody Update update){
        log.info(String.format("Received update %s", update.toString()));
        return songsTopicBot.onWebhookUpdateReceived(update);
    }
}
