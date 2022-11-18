package com.example.telegrambot.service;

import com.example.telegrambot.model.Song;
import com.example.telegrambot.utils.ParserCategories;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class ParserService {
    @Value("${application.parser.port}")
    String parserPort;

    @Cacheable(value = "Songs", key = "#category")
    public List<Song> getSongs(ParserCategories category){
        RestTemplate restTemplate = new RestTemplate();
        String parserPath = String.format("http://localhost:%s/%s",
                parserPort, category.getTitle().toLowerCase());

        ResponseEntity<Song[]> response =
                restTemplate.getForEntity(parserPath, Song[].class);
        Song[] songsAsArray = response.getBody();
        return Arrays.asList(songsAsArray);
    }
}
