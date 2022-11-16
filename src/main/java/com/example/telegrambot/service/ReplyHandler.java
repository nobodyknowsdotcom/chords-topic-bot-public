package com.example.telegrambot.service;

import com.example.telegrambot.model.Song;
import com.example.telegrambot.utils.ButtonsReply;
import com.example.telegrambot.utils.ParserCategories;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class ReplyHandler {
    @Value("${application.songs-topic-size}")
    private int topicSize;
    private final ParserService parserService;

    public ReplyHandler(ParserService parser) {
        this.parserService = parser;
    }

    public SendMessage handleMessage(String incomingMessageText, Long chatId){
        SendMessage messageToSend = new SendMessage();
        List<Song> songsList = new ArrayList<>();
        messageToSend.setChatId(chatId.toString());

        switch (incomingMessageText){
            case "/start":
                messageToSend.setText(ButtonsReply.START.getTitle());
                break;
            case "Что делает этот бот? \uD83E\uDD14":
                messageToSend.setText(ButtonsReply.HELP.getTitle());
                break;
            case "Получить топ разборов песен за сегодня":
                messageToSend.setText(ButtonsReply.TOPIC_FOR_TODAY.getTitle());
                songsList = parserService.getSongsTopic(ParserCategories.TODAY, topicSize);
                break;
            case "Получить топ разборов песен за неделю":
                messageToSend.setText(ButtonsReply.TOPIC_FOR_WEEK.getTitle());
                songsList = parserService.getSongsTopic(ParserCategories.WEEK, topicSize);
                break;
            case "Получить топ разборов песен за месяц":
                messageToSend.setText(ButtonsReply.TOPIC_FOR_MONTH.getTitle());
                songsList = parserService.getSongsTopic(ParserCategories.MONTH, topicSize);
                break;
            case "Получить топ разборов песен за все время":
                messageToSend.setText(ButtonsReply.TOPIC_FOR_ALL_TIME.getTitle());
                songsList = parserService.getSongsTopic(ParserCategories.ALL, topicSize);
                break;
            default:
                messageToSend.setText("Братик, что ты делаешь? Я не могу обработать твоё сообщение :(");
                ReplyKeyboardMarkup keyboardButtons = ReplyKeyboardService.getKeyboardMarkup();
                messageToSend.setReplyMarkup(keyboardButtons);
        }

        if(!songsList.isEmpty()){
            CallbackButtonService.addSongsAsButtons(messageToSend, songsList);
        }

        return messageToSend;
    }
}
