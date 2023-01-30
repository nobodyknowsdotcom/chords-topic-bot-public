package com.example.telegrambot.service;

import com.example.telegrambot.factory.PageDtoFactory;
import com.example.telegrambot.factory.SongsPageFactory;
import com.example.telegrambot.model.PageDto;
import com.example.telegrambot.model.SongsPage;
import com.example.telegrambot.enums.BotState;
import com.example.telegrambot.parserapi.ParserApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.ConnectException;

@Service
@Slf4j
public class ParserApiService {
    private final SongsPageFactory songsPageFactory;
    private final ParserApi parserApi;

    public ParserApiService(SongsPageFactory songsPageFactory, ParserApi parserApi) {
        this.songsPageFactory = songsPageFactory;
        this.parserApi = parserApi;
    }

    public SongsPage getDefaultTopicByCategory(BotState category){
        SongsPage songsPage = songsPageFactory.getFirstPageByCategory(category);

        PageDto pageDto = this.getPage(songsPage.toRequestUrl().strip());
        songsPage.updateFromDto(pageDto);
        return songsPage;
    }

    public PageDto getPage(String requestUrl){
        PageDto pageDto;

        try {
            ResponseEntity<PageDto> response = parserApi.makeRequest(requestUrl);
            pageDto = response.getBody();
        } catch (ConnectException e){
            log.error(String.format("Cant process %s request to parser", requestUrl));
            log.error(e.getMessage());
            pageDto = PageDtoFactory.getEmptyPageDto();
        }
        return pageDto;
    }
}
