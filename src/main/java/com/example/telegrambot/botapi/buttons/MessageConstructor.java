package com.example.telegrambot.botapi.buttons;

import com.example.telegrambot.model.SongsTopic;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

@Service
public class MessageConstructor {
    public static void addCallbackButtons(SendMessage message, SongsTopic topic){
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> buttonRows = Callback.getSongsAsButtons(topic.getSongs());
        List<InlineKeyboardButton> paginationButtons = Pagination.getPaginationButtons(topic);

        buttonRows.add(paginationButtons);
        markup.setKeyboard(buttonRows);
        message.setReplyMarkup(markup);
    }
}
