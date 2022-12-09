package com.example.telegrambot.botapi.buttons;

import com.example.telegrambot.model.SongsTopic;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

@Component
public class MessageConstructor {
    public static void addCallbackButtons(SendMessage message, SongsTopic topic){
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> buttonRows = SongToButtonConverter.getSongsAsButtons(topic.getSongs());
        List<InlineKeyboardButton> paginationButtons = PaginationButtonsConstructor.getPaginationButtons(topic, message.getChatId());

        buttonRows.add(paginationButtons);
        markup.setKeyboard(buttonRows);
        message.setReplyMarkup(markup);
    }
}
