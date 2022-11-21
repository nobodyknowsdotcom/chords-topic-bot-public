package com.example.telegrambot.caching;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CacheCleaner {
    @Scheduled(cron = "${cache-cleaner.config}",zone = "GMT+5")
    @CacheEvict(value = "Songs", allEntries=true)
    public void clearCache() {
        log.info("Clearing cache...");
    }
}
