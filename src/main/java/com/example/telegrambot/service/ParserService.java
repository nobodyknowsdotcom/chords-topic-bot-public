package com.example.telegrambot.service;

import com.example.telegrambot.model.Song;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class ParserService {
    @Value("${parser.port}")
    String parserPort;

    public Song[] getSongsByApi(String type){
        RestTemplate restTemplate = new RestTemplate();
        String parserPath = String.format("http://localhost:%s/%s",
                parserPort, type.toLowerCase());

        ResponseEntity<Song[]> response =
                restTemplate.getForEntity(parserPath, Song[].class);

        return response.getBody();
    }
}
