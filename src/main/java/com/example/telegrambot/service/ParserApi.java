package com.example.telegrambot.service;

import com.example.telegrambot.model.PageDto;
import com.example.telegrambot.model.SongsPage;
import com.example.telegrambot.utils.BotState;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class ParserApi {
    @Value("${parser.port}")
    private String parserPort;
    @Value("${parser.request.size}")
    private int size;
    @Value("${parser.request.sort}")
    private String sort;

    public SongsPage getDefaultTopicByCategory(BotState category){
        SongsPage songsPage = new SongsPage(category, 0, size, sort);

        PageDto pageDto = getPage(songsPage.toRequestUrl());
        songsPage.updateFromDto(pageDto);
        return songsPage;
    }
    @Cacheable(value = "Songs", key = "#requestUrl")
    public PageDto getPage(String requestUrl){
        ResponseEntity<PageDto> response = makeRequestToParserApi(requestUrl);
        PageDto topicPageDto = response.getBody();

        if (topicPageDto != null) {
            return topicPageDto;
        }
        else {
            log.info("Got empty dto from parser service");
            return new PageDto();
        }
    }

    private ResponseEntity<PageDto> makeRequestToParserApi(String requestUrl){
        RestTemplate restTemplate = new RestTemplate();
        String parserPath = String.format("http://localhost:%s/%s",
                parserPort, requestUrl);

        return restTemplate.getForEntity(parserPath, PageDto.class);
    }
}
