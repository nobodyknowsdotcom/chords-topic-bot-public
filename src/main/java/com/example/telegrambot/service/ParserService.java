package com.example.telegrambot.service;

import com.example.telegrambot.dto.PageDto;
import com.example.telegrambot.model.SongsTopic;
import com.example.telegrambot.utils.TopicCategories;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class ParserService {
    @Value("${parser.port}")
    private String parserPort;
    @Value("${parser.request.size}")
    private int size;
    @Value("${parser.request.sort}")
    private String sort;

    public SongsTopic getDefaultTopicByCategory(TopicCategories category){
        SongsTopic topic = new SongsTopic(category, 0, size, sort);
        PageDto page = getTopicPage(topic.toRequestUrl());
        topic.updateFromDto(page);
        return topic;
    }
    @Cacheable(value = "Songs", key = "#requestUrl")
    public PageDto getTopicPage(String requestUrl){
        ResponseEntity<PageDto> response = makeRequestToParserApi(requestUrl);
        PageDto topicPage = response.getBody();

        if (topicPage != null) {
            return topicPage;
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
