package com.example.telegrambot.service;

import com.example.telegrambot.model.SongsTopic;
import com.example.telegrambot.utils.ReplyToUser;
import com.example.telegrambot.utils.TopicCategories;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;


@Slf4j
@Component
public class ReplyKeyboardButtonsHandler {
    private final ParserService parserService;

    public ReplyKeyboardButtonsHandler(ParserService parser) {
        this.parserService = parser;
    }

    public SendMessage handleMessage(String incomingMessageText, Long chatId){
        SendMessage messageToSend = new SendMessage();
        SongsTopic topic = new SongsTopic();

        messageToSend.setChatId(chatId.toString());

        switch (incomingMessageText) {
            case "/start" -> {
                messageToSend.setText(ReplyToUser.START.getTitle());
                setupReplyKeyboardMarkup(messageToSend);
            }
            case "/help" -> {
                messageToSend.setText(ReplyToUser.HELP.getTitle());
                setupReplyKeyboardMarkup(messageToSend);
            }
            case "Хочу топ разборов песен за сегодня \uD83E\uDD20" -> {
                messageToSend.setText(ReplyToUser.TOPIC_FOR_TODAY.getTitle());
                topic = parserService.getDefaultTopicByCategory(TopicCategories.TODAY);
            } case "Хочу топ разборов песен за неделю ☺️" -> {
                messageToSend.setText(ReplyToUser.TOPIC_FOR_WEEK.getTitle());
                topic = parserService.getDefaultTopicByCategory(TopicCategories.WEEK);
            }
            case "Хочу топ разборов песен за месяц \uD83E\uDD78" -> {
                messageToSend.setText(ReplyToUser.TOPIC_FOR_MONTH.getTitle());
                topic = parserService.getDefaultTopicByCategory(TopicCategories.MONTH);
            }
            case "Хочу топ разборов песен за все время \uD83E\uDD79" -> {
                messageToSend.setText(ReplyToUser.TOPIC_FOR_ALL_TIME.getTitle());
                topic = parserService.getDefaultTopicByCategory(TopicCategories.ALL);
            }
            default -> {
                messageToSend.setText("Братик, что ты делаешь? Я не могу обработать твоё сообщение :(");
                setupReplyKeyboardMarkup(messageToSend);
            }
        }

        if(!topic.getSongs().isEmpty()){
            CallbackButtonService.addSongsAsButtons(messageToSend, topic.getSongs());
        }

        return messageToSend;
    }

    private static void setupReplyKeyboardMarkup(SendMessage messageToSend) {
        ReplyKeyboardMarkup keyboardButtons;
        keyboardButtons = ReplyKeyboardService.getKeyboardMarkup();
        messageToSend.setReplyMarkup(keyboardButtons);
    }
}
