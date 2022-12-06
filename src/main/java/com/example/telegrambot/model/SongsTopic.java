package com.example.telegrambot.model;


import com.example.telegrambot.dto.Page;
import com.example.telegrambot.utils.TopicCategories;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SongsTopic {
    private List<Song> songs;
    private TopicCategories category;
    private int page;
    private int size;
    private String sort;
    private boolean hasNext;
    private boolean hasPrevious;

    public SongsTopic(TopicCategories category, int page, int size, String sort){
        this.category = category;
        this.page = page;
        this.size = size;
        this.sort = sort;
    }

    public void incrementPage(){
        page += 1;
    }

    public void decrementPage(){
        page -= 1;
    }

    public String toRequestUrl(){
        return String.format("%s?page=%s&size=%s&sort=%s", category, page, size, sort);
    }

    public void updateFromDto(Page dto){
        this.songs = dto.getSongs();
        this.hasNext = dto.isHasNext();
        this.hasPrevious = dto.isHasPrevious();
    }
}
