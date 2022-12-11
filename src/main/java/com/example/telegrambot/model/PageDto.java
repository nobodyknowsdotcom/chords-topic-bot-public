package com.example.telegrambot.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PageDto {
    List<Song> songs;
    boolean hasNext;
    boolean hasPrevious;
}
