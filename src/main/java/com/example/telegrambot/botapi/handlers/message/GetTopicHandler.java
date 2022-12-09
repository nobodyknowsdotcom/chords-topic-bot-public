package com.example.telegrambot.botapi.handlers.message;

import com.example.telegrambot.botapi.buttons.ButtonsFactory;
import com.example.telegrambot.botapi.handlers.InputMessageHandler;
import com.example.telegrambot.model.SongsPage;
import com.example.telegrambot.service.ParserApi;
import com.example.telegrambot.utils.BotState;
import com.example.telegrambot.utils.MessageType;
import com.example.telegrambot.utils.ReplyToUser;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
public class GetTopicHandler implements InputMessageHandler {
    private final ParserApi parserApi;

    public GetTopicHandler(ParserApi parserApi) {
        this.parserApi = parserApi;
    }

    @Override
    public SendMessage handle(long chatId, BotState state) {
        SendMessage messageToSend = new SendMessage();
        SongsPage songsPage = new SongsPage();
        messageToSend.setChatId(chatId);

        switch (state) {
            case TODAY -> {
                messageToSend.setText(ReplyToUser.TOPIC_FOR_TODAY.getTitle());
                songsPage = parserApi.getDefaultTopicByCategory(BotState.TODAY);
            }
            case WEEK -> {
                messageToSend.setText(ReplyToUser.TOPIC_FOR_WEEK.getTitle());
                songsPage = parserApi.getDefaultTopicByCategory(BotState.WEEK);
            }
            case MONTH -> {
                messageToSend.setText(ReplyToUser.TOPIC_FOR_MONTH.getTitle());
                songsPage = parserApi.getDefaultTopicByCategory(BotState.MONTH);
            }
            case ALL -> {
                messageToSend.setText(ReplyToUser.TOPIC_FOR_ALL_TIME.getTitle());
                songsPage = parserApi.getDefaultTopicByCategory(BotState.ALL);
            }
            default -> {
                messageToSend.setText("Братик, что ты делаешь? Я не могу обработать твоё сообщение :(");
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
