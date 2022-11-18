package com.example.telegrambot.service;

import com.example.telegrambot.model.Song;
import com.example.telegrambot.model.SongsTopic;
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
    @Value("${application.topic-page-capability}")
    private int topicPageCapability;
    private final ParserService parser;

    public ReplyHandler(ParserService parser) {
        this.parser = parser;
    }

    public SendMessage handleMessage(String incomingMessageText, Long chatId){
        SendMessage messageToSend = new SendMessage();

        List<Song> songsList = new ArrayList<>();

        messageToSend.setChatId(chatId.toString());

        switch (incomingMessageText){
            case "/start":
                messageToSend.setText(ButtonsReply.START.getTitle());
                setupReplyKeyboardMarkup(messageToSend);
                break;
            case "/help":
                messageToSend.setText(ButtonsReply.HELP.getTitle());
                setupReplyKeyboardMarkup(messageToSend);
                break;
            case "Хочу топ разборов песен за сегодня \uD83E\uDD20":
                messageToSend.setText(ButtonsReply.TOPIC_FOR_TODAY.getTitle());
                songsList = parser.getSongs(ParserCategories.TODAY);
                break;
            case "Хочу топ разборов песен за неделю ☺️":
                messageToSend.setText(ButtonsReply.TOPIC_FOR_WEEK.getTitle());
                songsList = parser.getSongs(ParserCategories.WEEK);
                break;
            case "Хочу топ разборов песен за месяц \uD83E\uDD78":
                messageToSend.setText(ButtonsReply.TOPIC_FOR_MONTH.getTitle());
                songsList = parser.getSongs(ParserCategories.MONTH);
                break;
            case "Хочу топ разборов песен за все время \uD83E\uDD79":
                messageToSend.setText(ButtonsReply.TOPIC_FOR_ALL_TIME.getTitle());
                songsList = parser.getSongs(ParserCategories.ALL);
                break;
            default:
                messageToSend.setText("Братик, что ты делаешь? Я не могу обработать твоё сообщение :(");
                setupReplyKeyboardMarkup(messageToSend);
        }

        if(!songsList.isEmpty()){
            SongsTopic songsTopic = new SongsTopic(songsList, topicPageCapability);
            CallbackButtonService.addSongsAsButtons(messageToSend, songsTopic.getActualTopic());
        }

        return messageToSend;
    }

    private static void setupReplyKeyboardMarkup(SendMessage messageToSend) {
        ReplyKeyboardMarkup keyboardButtons;
        keyboardButtons = ReplyKeyboardService.getKeyboardMarkup();
        messageToSend.setReplyMarkup(keyboardButtons);
    }
}
