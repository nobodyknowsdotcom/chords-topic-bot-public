package com.example.telegrambot.model;


import com.example.telegrambot.utils.BotState;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;

import java.nio.charset.StandardCharsets;
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

    public void updateFromQuery(String query){
        this.category = BotState.valueOf(query.split("\\?")[0]);
        List<NameValuePair> params = URLEncodedUtils.parse(query, StandardCharsets.UTF_8);
        this.pagination.setPage(Integer.parseInt(params.get(0).getValue()));
    }
}
