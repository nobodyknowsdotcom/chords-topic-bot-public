package com.example.telegrambot.botapi.buttons;

import com.example.telegrambot.model.Pagination;
import com.example.telegrambot.model.SongsPage;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Component
public class PaginationButtonsFactory {
    public static List<InlineKeyboardButton> getPaginationButtons(SongsPage songsPage){
        List<InlineKeyboardButton> paginationRow = new ArrayList<>();
        Pagination pagination = songsPage.getPagination();


        if(pagination.isHasPrevious()){
            paginationRow.add(getPaginationButton(songsPage.getPreviousPageUrl(), "\uD83D\uDC48"));
        }
        if(pagination.isHasNext()){
            paginationRow.add(getPaginationButton(songsPage.getNextPageUrl(),"\uD83D\uDC49"));
        }
        return paginationRow;
    }



    private static InlineKeyboardButton getPaginationButton(String callbackData, String emoji){
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setCallbackData(callbackData);
        button.setText(emoji);
        return button;
    }
}
