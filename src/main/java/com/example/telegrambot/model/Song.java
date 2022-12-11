package com.example.telegrambot.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class Song {
    String name;
    String artist;
    String url;
    int position;

    @Override
    public String toString() {
        return String.format("%s. %s - %s", position, artist, name);
    }
}
