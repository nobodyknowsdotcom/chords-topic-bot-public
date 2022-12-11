package com.example.telegrambot.model;


import com.example.telegrambot.utils.BotState;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SongsPage {
    List<Song> songs;
    BotState category;
    Pagination pagination;

    public SongsPage(BotState category, int page, int size, String sort){
        this.category = category;
        this.pagination = new Pagination(page, size, sort);
    }

    public String toRequestUrl(){
        return String.format("%s?%s", category, pagination.toQueryParams());
    }

    public String getNextPageUrl(){
        return String.format("%s?%s", category, pagination.getQueryParamsForNextPage());
    }

    public String getPreviousPageUrl(){
        return String.format("%s?%s", category, pagination.getQueryParamsForPreviousPage());
    }

    public void updateFromDto(PageDto dto){
        this.songs = dto.getSongs();
        pagination.setHasNext(dto.isHasNext());
        pagination.setHasPrevious(dto.isHasPrevious());
    }
}
