package com.example.telegrambot.factory;

import com.example.telegrambot.model.PageDto;

import java.util.ArrayList;

public class PageDtoFactory {
    public static PageDto getEmptyPageDto(){
        PageDto pageDto = new PageDto();
        pageDto.setSongs(new ArrayList<>());
        pageDto.setHasNext(false);
        pageDto.setHasPrevious(false);

        return pageDto;
    }
}
