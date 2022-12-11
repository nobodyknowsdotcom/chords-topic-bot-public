package com.example.telegrambot.botapi.handlers.query;

import com.example.telegrambot.botapi.buttons.ButtonsFactory;
import com.example.telegrambot.botapi.handlers.QueryHandler;
import com.example.telegrambot.model.PageDto;
import com.example.telegrambot.model.Pagination;
import com.example.telegrambot.model.SongsPage;
import com.example.telegrambot.service.ParserApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

@Component
@Slf4j
public class PaginationQueryHandler implements QueryHandler {
    @Value("${parser.request.sort}")
    private String sort;
    @Value("${parser.request.size}")
    private int size;
    private final ParserApi parserApi;

    public PaginationQueryHandler(ParserApi parserApi) {
        this.parserApi = parserApi;
    }

    public EditMessageReplyMarkup handle(CallbackQuery callbackQuery){
        EditMessageReplyMarkup editMessageReplyMarkup = new EditMessageReplyMarkup();
        Message oldMessage = callbackQuery.getMessage();


        SongsPage songsPage = new SongsPage();
        Pagination pagination = new Pagination();
        pagination.setSort("position");
        pagination.setSize(size);
        songsPage.setPagination(pagination);


        PageDto pageDto = parserApi.getPage(callbackQuery.getData());
        // Обновляем наличие соседних страниц и список песен, полученные от парсера
        songsPage.updateFromDto(pageDto);
        // Обновляем категорию и номер страницы из параметров запроса
        songsPage.updateFromQuery(callbackQuery.getData());
        InlineKeyboardMarkup markup = ButtonsFactory.getCallbackButtons(songsPage);


        editMessageReplyMarkup.setChatId(oldMessage.getChatId());
        editMessageReplyMarkup.setReplyMarkup(markup);
        editMessageReplyMarkup.setMessageId(oldMessage.getMessageId());

        return editMessageReplyMarkup;
    }
}
