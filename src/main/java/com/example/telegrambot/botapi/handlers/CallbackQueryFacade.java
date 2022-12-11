package com.example.telegrambot.botapi.handlers;

import com.example.telegrambot.botapi.handlers.query.PaginationQueryHandler;
import com.example.telegrambot.utils.BotState;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

/**
 * Разбирает входящие запросы от кнопок, находит нужный обработчик
 * */
@Component
@Slf4j
public class CallbackQueryFacade {
    private final PaginationQueryHandler paginationQueryHandler;

    public CallbackQueryFacade(PaginationQueryHandler paginationQueryHandler) {
        this.paginationQueryHandler = paginationQueryHandler;
    }

    public BotApiMethod handleCallbackQuery(CallbackQuery callbackQuery){
        String queryData = callbackQuery.getData();
        log.info(String.format("Processing %s event...", queryData));

        if(isPaginationQuery(queryData)){
           return paginationQueryHandler.handle(callbackQuery);
        }

        log.error(String.format("Not found query handler for query '%s'", queryData));
        return null;
    }

    private boolean isPaginationQuery(String query){
        BotState state;
        String queryToParse = query.split("\\?")[0].strip();
        try{
            state = BotState.valueOf(queryToParse);
        } catch (IllegalArgumentException e){
            return false;
        }

        return switch (state){
            case TODAY, WEEK, MONTH, ALL -> true;
            default -> false;
        };
    }
}
