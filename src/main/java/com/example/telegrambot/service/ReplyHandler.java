package com.example.telegrambot.service;

import com.example.telegrambot.model.Song;
import com.example.telegrambot.service.callback.CallbackButtonSetter;
import com.example.telegrambot.utils.ButtonsReply;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class ReplyHandler {
    private final ParserService parserService;

    public ReplyHandler(ParserService parser, CallbackButtonSetter callbackButtonSetter) {
        this.parserService = parser;
    }

    public SendMessage handleMessage(String text, Long chatId){
        SendMessage messageToSend = new SendMessage();
        messageToSend.setChatId(chatId.toString());

        switch (text){
            case "/start":
                messageToSend.setText(ButtonsReply.START.getTitle());
                addReplyKeyboardMarkup(messageToSend);
                break;
            case "Что делает этот бот? \uD83E\uDD14":
                messageToSend.setText(ButtonsReply.HELP.getTitle());
                break;
            case "Дай мне топ разборов песен на сегодня! \uD83C\uDFB8":
                messageToSend.setText(ButtonsReply.TOPIC_FOR_TODAY.getTitle());
                List<Song> songs = Arrays.stream(parserService.getSongsByApi("today"))
                        .limit(10)
                        .collect(Collectors.toList());
                CallbackButtonSetter.addSongsAsButtons(messageToSend, songs);
                break;
            default:
                messageToSend.setText("Братик, что ты делаешь? Я не могу обработать твоё сообщение :(");
        }

        return messageToSend;
    }

    private static void addReplyKeyboardMarkup(SendMessage message){
        ReplyKeyboardMarkup keyboardMarkup = getKeyboardMarkup();
        message.setReplyMarkup(keyboardMarkup);
    }

    private static ReplyKeyboardMarkup getKeyboardMarkup(){
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow keyboardFirstRow = new KeyboardRow();
        keyboardFirstRow.add("Дай мне топ разборов песен на сегодня! \uD83C\uDFB8");
        keyboard.add(keyboardFirstRow);
        KeyboardRow keyboardSecondRow = new KeyboardRow();
        keyboardSecondRow.add("Что делает этот бот? \uD83E\uDD14");
        keyboard.add(keyboardSecondRow);

        keyboardMarkup.setKeyboard(keyboard);
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(false);
        return keyboardMarkup;
    }
}
