package com.example.telegrambot.parserapi;

import com.example.telegrambot.model.PageDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.ConnectException;

@Service
@Slf4j
public class ParserApi {
    @Value("${parser.port}")
    private String parserPort;
    @Value("${parser.hostname}")
    private String parserHostname;
    @Cacheable(value = "songs")
    public ResponseEntity<PageDto> makeRequest(String requestUrl) throws ConnectException {
        log.info(String.format("Calling parser api with %s query...", requestUrl));

        RestTemplate restTemplate = new RestTemplate();
        String parserPath = String.format("http://%s:%s/%s",
                parserHostname, parserPort, requestUrl);

        return restTemplate.getForEntity(parserPath, PageDto.class);
    }
}
