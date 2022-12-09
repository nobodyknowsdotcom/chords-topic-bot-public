package com.example.telegrambot.model;

import com.example.telegrambot.model.Song;
import lombok.Data;

import java.util.List;

@Data
public class Page {
    private List<Song> songs;
    private boolean hasNext;
    private boolean hasPrevious;
}
