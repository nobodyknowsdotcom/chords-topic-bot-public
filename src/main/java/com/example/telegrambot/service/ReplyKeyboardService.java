package com.example.telegrambot.service;

import com.example.telegrambot.utils.ButtonsText;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReplyKeyboardService {
    public static ReplyKeyboardMarkup getKeyboardMarkup(){
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboard = new ArrayList<>();

        keyboard.add(getButton(ButtonsText.HELP.getTitle()));
        keyboard.add(getButton(ButtonsText.TOPIC_FOR_TODAY.getTitle()));
        keyboard.add(getButton(ButtonsText.TOPIC_FOR_WEEK.getTitle()));
        keyboard.add(getButton(ButtonsText.TOPIC_FOR_MONTH.getTitle()));
        keyboard.add(getButton(ButtonsText.TOPIC_FOR_ALL_TIME.getTitle()));

        keyboardMarkup.setKeyboard(keyboard);
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(false);
        return keyboardMarkup;
    }

    private static KeyboardRow getButton(String text){
        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add(text);
        return keyboardRow;
    }
}
