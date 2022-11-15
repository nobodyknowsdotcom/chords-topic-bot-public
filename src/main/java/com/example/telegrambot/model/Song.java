package com.example.telegrambot.model;

import lombok.Data;

@Data
public class Song {
    private String name;
    private String artist;
    private String url;

    @Override
    public String toString() {
        return String.format("%s - %s", artist, name);
    }
}
