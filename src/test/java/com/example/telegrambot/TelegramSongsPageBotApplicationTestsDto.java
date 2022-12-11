package com.example.telegrambot;

import com.example.telegrambot.model.SongsPage;
import com.example.telegrambot.utils.BotState;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TelegramSongsPageBotApplicationTestsDto {
    @Value("${parser.request.size}")
    private int size;
    @Value("${parser.request.sort}")
    private String sort;

    @Test
    void testTopicUrlGenerator() {
        SongsPage topic = new SongsPage(BotState.WEEK, 1, size, sort);
        String rightRequestUrl = String.format("WEEK?page=1&size=%s&sort=%s", size, sort);
        assertThat(topic.toRequestUrl()).isEqualTo(rightRequestUrl);
    }

}
