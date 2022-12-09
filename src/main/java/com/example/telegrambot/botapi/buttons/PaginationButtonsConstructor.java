package com.example.telegrambot.botapi.buttons;

import com.example.telegrambot.model.SongsTopic;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Component
public class PaginationButtonsConstructor {
    public static List<InlineKeyboardButton> getPaginationButtons(SongsTopic topic, String chatId){
        List<InlineKeyboardButton> paginationRow = new ArrayList<>();

        if(topic.isHasPrevious()){
            paginationRow.add(getBackButton(chatId));
        }
        if(topic.isHasNext()){
            paginationRow.add(getForwardButton(chatId));
        }
        return paginationRow;
    }

    private static InlineKeyboardButton getBackButton(String chatId){
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setCallbackData(String.format("back/%s", chatId));
        button.setText("⏪️");
        return button;
    }

    private static InlineKeyboardButton getForwardButton(String chatId){
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setCallbackData(String.format("forward/%s", chatId));
        button.setText("⏩");
        return button;
    }
}
