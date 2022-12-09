package com.example.telegrambot.botapi.buttons;

import com.example.telegrambot.model.SongsPage;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Component
public class PaginationButtonsConstructor {
    public static List<InlineKeyboardButton> getPaginationButtons(SongsPage topic, String chatId){
        List<InlineKeyboardButton> paginationRow = new ArrayList<>();

        if(topic.isHasPrevious()){
            paginationRow.add(getPaginationButton(chatId, "\uD83D\uDC48"));
        }
        else{
            paginationRow.add(getPaginationButton(chatId, "\uD83D\uDE45\u200D♀️"));
        }

        if(topic.isHasNext()){
            paginationRow.add(getPaginationButton(chatId,"\uD83D\uDC49"));
        }
        else{
            paginationRow.add(getPaginationButton(chatId,"\uD83D\uDE45\u200D♀️"));
        }
        return paginationRow;
    }

    private static InlineKeyboardButton getPaginationButton(String chatId, String emoji){
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setCallbackData(String.format("back/%s", chatId));
        button.setText(emoji);
        return button;
    }
}
