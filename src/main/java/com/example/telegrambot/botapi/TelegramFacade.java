package com.example.telegrambot.botapi;

import com.example.telegrambot.botapi.handlers.CallbackQueryFacade;
import com.example.telegrambot.enums.BotState;
import com.example.telegrambot.enums.ReplyToUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;


@Slf4j
@Component
public class TelegramFacade {
    private final BotStateContext botStateContext;
    private final CallbackQueryFacade callbackQueryFacade;

    public TelegramFacade(BotStateContext botStateContext, CallbackQueryFacade callbackQueryFacade) {
        this.botStateContext = botStateContext;
        this.callbackQueryFacade = callbackQueryFacade;
    }

    public BotApiMethod handleUpdate(Update update){
        if(update.hasCallbackQuery()){
            EditMessageReplyMarkup response = callbackQueryFacade.handleCallbackQuery(update.getCallbackQuery());
            log.info(String.format("Send pagination update %s", response.getChatId()));
            return response;
        }

        if(update.hasMessage() && update.getMessage().hasText()){
            SendMessage response = handleInputMessage(update.getMessage());
            log.info(String.format("Send message %s to %s", response.getText(), response.getChatId()));
            return response;
        }
        log.info(String.format("Got empty message %s", update));
        return getEmptyMessageTemplate(update);
    }

    public SendMessage handleInputMessage(Message message){
        BotState state;

        switch (message.getText()) {
            case "/start" -> state = BotState.START;
            case "/help" -> state = BotState.HELP;
            case "Хочу топ разборов песен за сегодня \uD83E\uDD20" -> state = BotState.TODAY;
            case "Хочу топ разборов песен за неделю ☺️" -> state = BotState.WEEK;
            case "Хочу топ разборов песен за месяц \uD83E\uDD78" -> state = BotState.MONTH;
            case "Хочу топ разборов песен за все время \uD83E\uDD79" -> state = BotState.ALL;
            default -> state = BotState.ERROR;
        }

        return botStateContext.processInputMessage(state, message);
    }

    private SendMessage getEmptyMessageTemplate(Update update){
        SendMessage response = new SendMessage();
        response.setText(ReplyToUser.ERROR.getTitle());
        try{
            response.setChatId(update.getMessage().getChatId());
        } catch (NullPointerException e){
            response.setChatId(update.getMyChatMember().getChat().getId());
        }
        return response;
    }
}
