package com.example.telegrambot.model;

import lombok.Data;

@Data
public class Song {
    private String name;
    private String artist;
    private String url;
    private int position;

    @Override
    public String toString() {
        return String.format("%s. %s - %s", position, artist, name);
    }
}
