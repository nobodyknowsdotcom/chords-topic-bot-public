package com.example.telegrambot.dto;

import com.example.telegrambot.model.Song;
import lombok.Data;

import java.util.List;

@Data
public class PageDto {
    private List<Song> songs;
    private boolean hasNext;
    private boolean hasPrevious;
}
