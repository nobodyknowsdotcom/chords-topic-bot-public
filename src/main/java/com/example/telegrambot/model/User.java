package com.example.telegrambot.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("users")
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class User {
    long chatId;
    SongsPage topic;
}
