package com.example.telegrambot.model;

import com.example.telegrambot.model.SongsTopic;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    long chatId;
    SongsTopic topic;
}
