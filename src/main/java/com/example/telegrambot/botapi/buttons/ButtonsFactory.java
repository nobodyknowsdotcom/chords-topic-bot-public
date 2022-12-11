package com.example.telegrambot.botapi.buttons;

import com.example.telegrambot.model.SongsPage;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

@Component
public class ButtonsFactory {
    public static void addCallbackButtons(SendMessage message, SongsPage songsPage){
        InlineKeyboardMarkup buttons = getCallbackButtons(songsPage);
        message.setReplyMarkup(buttons);
    }

    public static InlineKeyboardMarkup getCallbackButtons(SongsPage songsPage){
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> buttonRows = SongToButtonConverter.getSongsAsButtons(songsPage.getSongs());
        List<InlineKeyboardButton> paginationButtons = PaginationButtonsFactory.getPaginationButtons(songsPage);

        buttonRows.add(paginationButtons);
        markup.setKeyboard(buttonRows);
        return markup;
    }

    public static void addReplyKeyboardMarkup(SendMessage message){
        ReplyKeyboardMarkup keyboardButtons;
        keyboardButtons = ReplyKeyboardFactory.getKeyboardMarkup();
        message.setReplyMarkup(keyboardButtons);
    }
}
