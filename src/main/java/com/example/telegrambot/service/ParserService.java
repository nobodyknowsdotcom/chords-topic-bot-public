package com.example.telegrambot.service;

import com.example.telegrambot.dto.Song;
import com.example.telegrambot.utils.ParserCategories;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class ParserService {
    @Value("${parser.port}")
    private String parserPort;

    @Cacheable(value = "Songs", key = "#category.title")
    public List<Song> getSongs(ParserCategories category){
        ResponseEntity<Song[]> response = getSongsFromParserApi(category);
        Song[] songsAsArray = response.getBody();

        if (songsAsArray != null) {
            return Arrays.asList(songsAsArray);
        }
        else return new ArrayList<>();
    }

    private ResponseEntity<Song[]> getSongsFromParserApi(ParserCategories category){
        RestTemplate restTemplate = new RestTemplate();
        String parserPath = String.format("http://localhost:%s/%s",
                parserPort, category.getTitle().toLowerCase());

        return restTemplate.getForEntity(parserPath, Song[].class);
    }
}
