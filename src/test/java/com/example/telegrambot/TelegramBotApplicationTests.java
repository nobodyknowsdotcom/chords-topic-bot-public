package com.example.telegrambot;

import com.example.telegrambot.model.SongsTopic;
import com.example.telegrambot.utils.TopicCategories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TelegramBotApplicationTests {
    @Value("${parser.request.size}")
    private int size;
    @Value("${parser.request.sort}")
    private String sort;

    @Test
    void testTopicUrlGenerator() {
        SongsTopic topic = new SongsTopic(TopicCategories.WEEK, 1, size, sort);
        String rightRequestUrl = String.format("WEEK?page=1&size=%s&sort=%s", size, sort);
        assertThat(topic.toRequestUrl()).isEqualTo(rightRequestUrl);
    }

}
