package com.example.telegrambot.botapi.handlers.query;

import com.example.telegrambot.botapi.buttons.ButtonsFactory;
import com.example.telegrambot.botapi.handlers.QueryHandler;
import com.example.telegrambot.factory.SongsPageFactory;
import com.example.telegrambot.model.PageDto;
import com.example.telegrambot.model.SongsPage;
import com.example.telegrambot.service.ParserApiService;
import com.example.telegrambot.utils.BotState;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

@Component
public class PaginationQueryHandler implements QueryHandler {
    private final SongsPageFactory songsPageFactory;
    private final ParserApiService parserApiService;

    public PaginationQueryHandler(ParserApiService parserApiService, SongsPageFactory songsPageFactory) {
        this.parserApiService = parserApiService;
        this.songsPageFactory = songsPageFactory;
    }

    public EditMessageReplyMarkup handle(CallbackQuery callbackQuery){
        EditMessageReplyMarkup editMessageReplyMarkup = new EditMessageReplyMarkup();
        Message oldMessage = callbackQuery.getMessage();

        SongsPage songsPage = songsPageFactory.getFirstPageByCategory(BotState.TODAY);

        // Получаем от парсера следующую страницу
        PageDto pageDto = parserApiService.getPage(callbackQuery.getData());
        // Обновляем сообщение (кнопки пагинации и список песен) из которого вызван ивент
        songsPage.updateFromDto(pageDto);
        // Обновляем сообщение (номер страницы и категорию) из параметров запроса
        songsPage.updateFromQuery(callbackQuery.getData());
        InlineKeyboardMarkup markup = ButtonsFactory.getCallbackButtons(songsPage);


        editMessageReplyMarkup.setChatId(oldMessage.getChatId());
        editMessageReplyMarkup.setReplyMarkup(markup);
        editMessageReplyMarkup.setMessageId(oldMessage.getMessageId());

        return editMessageReplyMarkup;
    }
}
