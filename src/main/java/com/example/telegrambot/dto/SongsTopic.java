package com.example.telegrambot.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Getter
@Setter
public class SongsTopic {
    private List<Song> topic;
    private int topicPageCapability;
    private int currentPage;
    private int pagesCount;

    public SongsTopic(List<Song> topic, int topicPageCapability){
        this.topic = topic;
        this.currentPage = 1;
        this.topicPageCapability = topicPageCapability;
        this.pagesCount = calculatePagesCount();
    }

    public List<Song> getActualTopic(){
        return topic.subList((currentPage*topicPageCapability) - topicPageCapability, currentPage*topicPageCapability);
    }

    private int calculatePagesCount(){
        return (int) Math.ceil(topic.size()/topicPageCapability);
    }
}
