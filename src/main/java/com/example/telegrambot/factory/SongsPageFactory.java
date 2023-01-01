package com.example.telegrambot.factory;

import com.example.telegrambot.model.SongsPage;
import com.example.telegrambot.utils.BotState;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SongsPageFactory {
    @Value("${parser.request.size}")
    private int size;
    @Value("${parser.request.sort}")
    private String sort;

    public SongsPage getFirstPageByCategory(BotState category){
        return new SongsPage(category, 0, size, sort);
    }
}
