package com.example.telegrambot.botapi.handlers.messages;

import com.example.telegrambot.botapi.buttons.ButtonsFactory;
import com.example.telegrambot.botapi.handlers.InputMessageHandler;
import com.example.telegrambot.model.SongsPage;
import com.example.telegrambot.service.ParserApiService;
import com.example.telegrambot.utils.BotState;
import com.example.telegrambot.utils.MessageType;
import com.example.telegrambot.utils.ReplyToUser;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
public class GetTopicHandler implements InputMessageHandler {
    private final ParserApiService parserApiService;

    public GetTopicHandler(ParserApiService parserApiService) {
        this.parserApiService = parserApiService;
    }

    @Override
    public SendMessage handle(long chatId, BotState state) {
        SendMessage messageToSend = new SendMessage();
        SongsPage songsPage = new SongsPage();
        messageToSend.setChatId(chatId);

        switch (state) {
            case TODAY -> {
                messageToSend.setText(ReplyToUser.TOPIC_FOR_TODAY.getTitle());
                songsPage = parserApiService.getDefaultTopicByCategory(BotState.TODAY);
            }
            case WEEK -> {
                messageToSend.setText(ReplyToUser.TOPIC_FOR_WEEK.getTitle());
                songsPage = parserApiService.getDefaultTopicByCategory(BotState.WEEK);
            }
            case MONTH -> {
                messageToSend.setText(ReplyToUser.TOPIC_FOR_MONTH.getTitle());
                songsPage = parserApiService.getDefaultTopicByCategory(BotState.MONTH);
            }
            case ALL -> {
                messageToSend.setText(ReplyToUser.TOPIC_FOR_ALL_TIME.getTitle());
                songsPage = parserApiService.getDefaultTopicByCategory(BotState.ALL);
            }
            default -> {
                messageToSend.setText(ReplyToUser.OTHER.getTitle());
                ButtonsFactory.addReplyKeyboardMarkup(messageToSend);
            }
        }

        if(songsPage.getSongs() != null && !songsPage.getSongs().isEmpty()){
            ButtonsFactory.addCallbackButtons(messageToSend, songsPage);
        }

        return messageToSend;
    }

    @Override
    public MessageType getHandlerName() {
        return MessageType.TOPIC;
    }
}
