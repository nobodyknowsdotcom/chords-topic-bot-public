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
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();

        row.add("Это кнопка под клавиатурой.");
        keyboard.add(row);
        keyboardMarkup.setKeyboard(keyboard);
        keyboardMarkup.setResizeKeyboard(true);

        message.setReplyMarkup(keyboardMarkup);
    }
}
