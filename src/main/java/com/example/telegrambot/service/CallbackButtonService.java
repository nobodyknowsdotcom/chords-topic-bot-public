package com.example.telegrambot.service;

import com.example.telegrambot.dto.Song;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Component
public class CallbackButtonService {
    public static void addSongsAsButtons(SendMessage message, List<Song> songs){
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();

        for(Song song : songs){
            List<InlineKeyboardButton> rowInline = getSongAsButton(song);
            rowsInline.add(rowInline);
        }
        markupInline.setKeyboard(rowsInline);
        message.setReplyMarkup(markupInline);
    }

    private static List<InlineKeyboardButton> getSongAsButton(Song song){
        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        InlineKeyboardButton songButton = new InlineKeyboardButton();

        songButton.setText(song.toString());
        songButton.setUrl(song.getUrl());
        rowInline.add(songButton);
        return rowInline;
    }
}
