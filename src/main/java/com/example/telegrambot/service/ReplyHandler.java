package com.example.telegrambot.service;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReplyHandler {

    public static SendMessage handleMessage(String text, Long chatId){
        SendMessage messageToSend = new SendMessage();
        messageToSend.setChatId(chatId.toString());

        switch (text){
            case "/start":
                messageToSend.setText("Приветик, я бот-скидыватель аккордов и текста!" +
                        "Попроси топ аккордов и наслаждайся, надеюсь я сделал твой день чуть лучше :)");
                addReplyKeyboardMarkup(messageToSend);
                break;
            case "Что делает этот бот?":
                messageToSend.setText("Моя работа - кидать тебе самые популярные разборы песен с сайта AmDm.");
                break;
            case "Дай мне топ разборов песен на сегодня!":
                messageToSend.setText("Самые популярные разборы песен на сегодня:\n*разборы песен*");
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
        KeyboardRow row_1 = new KeyboardRow();
        KeyboardRow row_2 = new KeyboardRow();

        row_1.add("Дай мне топ разборов песен на сегодня!");
        keyboard.add(row_1);
        row_2.add("Что делает этот бот?");
        keyboard.add(row_2);
        keyboardMarkup.setKeyboard(keyboard);
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(false);

        return keyboardMarkup;
    }
}