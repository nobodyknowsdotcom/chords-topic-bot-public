package com.example.telegrambot.service;

import com.example.telegrambot.factory.PageDtoFactory;
import com.example.telegrambot.factory.SongsPageFactory;
import com.example.telegrambot.model.PageDto;
import com.example.telegrambot.model.SongsPage;
import com.example.telegrambot.utils.BotState;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.ConnectException;

@Service
@Slf4j
public class ParserApiService {
    @Value("${parser.port}")
    private String parserPort;
    private final SongsPageFactory songsPageFactory;

    public ParserApiService(SongsPageFactory songsPageFactory) {
        this.songsPageFactory = songsPageFactory;
    }

    public SongsPage getDefaultTopicByCategory(BotState category){
        SongsPage defaultPage = songsPageFactory.getFirstPageByCategory(category);

        PageDto pageDto = getPage(defaultPage.toRequestUrl());
        defaultPage.updateFromDto(pageDto);
        return defaultPage;
    }
    @Cacheable(value = "Songs", key = "#requestUrl")
    public PageDto getPage(String requestUrl){
        PageDto pageDto;

        try {
            ResponseEntity<PageDto> response = makeRequestToParserApi(requestUrl);
            pageDto = response.getBody();
        } catch (ConnectException e){
            log.error(String.format("Cant process %s request to parser", requestUrl));
            log.error(e.getMessage());
            pageDto = PageDtoFactory.getEmptyPageDto();
        }
        return pageDto;
    }

    private ResponseEntity<PageDto> makeRequestToParserApi(String requestUrl) throws ConnectException{
        RestTemplate restTemplate = new RestTemplate();
        String parserPath = String.format("http://parser:%s/%s",
                parserPort, requestUrl);

        return restTemplate.getForEntity(parserPath, PageDto.class);
    }
}
