package com.example.telegrambot.botapi.buttons;

import com.example.telegrambot.model.Song;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Component
public class Callback {
    public static List<List<InlineKeyboardButton>> getSongsAsButtons(List<Song> songs){
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        for(Song song : songs){
            List<InlineKeyboardButton> songAsButton = getSongAsButton(song);
            rowsInline.add(songAsButton);
        }
        return rowsInline;
    }

    private static List<InlineKeyboardButton> getSongAsButton(Song song){
        List<InlineKeyboardButton> row = new ArrayList<>();
        InlineKeyboardButton songButton = new InlineKeyboardButton();

        songButton.setText(song.toString());
        songButton.setUrl(song.getUrl());
        row.add(songButton);
        return row;
    }
}
