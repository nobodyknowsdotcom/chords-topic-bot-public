package com.example.telegrambot.service;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Component
public class MessageProcessing {

    public static SendMessage getEchoMessage(String text, Long chatId){
        SendMessage messageToSend = new SendMessage();
        messageToSend.setChatId(chatId.toString());
        messageToSend.setText(String.valueOf(text));

        addReplyKeyboardMarkup(messageToSend);
        return messageToSend;
    }

    private static void addReplyKeyboardMarkup(SendMessage message){
        ReplyKeyboardMarkup keyboardMarkup = getKeyboardMarkup();
        message.setReplyMarkup(keyboardMarkup);
    }

    private static ReplyKeyboardMarkup getKeyboardMarkup(){
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow row_1 = new KeyboardRow();
        KeyboardRow row_2 = new KeyboardRow();

        row_1.add("Дай мне топ разборов песен на сегодня!");
        keyboard.add(row_1);
        row_2.add("Как пользоваться этим ботом?");
        keyboard.add(row_2);
        keyboardMarkup.setKeyboard(keyboard);
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(false);

        return keyboardMarkup;
    }
}
