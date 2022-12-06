package com.example.telegrambot.botapi.buttons;

import com.example.telegrambot.model.SongsTopic;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Service
public class Pagination {
    public static List<InlineKeyboardButton> getPaginationButtons(SongsTopic topic){
        List<InlineKeyboardButton> paginationRow = new ArrayList<>();

        if(topic.isHasPrevious()){
            paginationRow.add(getBackButton());
        }
        if(topic.isHasNext()){
            paginationRow.add(getForwardButton());
        }
        return paginationRow;
    }

    private static InlineKeyboardButton getBackButton(){
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setCallbackData("back");
        button.setText("⏪️");
        return button;
    }

    private static InlineKeyboardButton getForwardButton(){
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setCallbackData("forward");
        button.setText("⏩");
        return button;
    }
}
