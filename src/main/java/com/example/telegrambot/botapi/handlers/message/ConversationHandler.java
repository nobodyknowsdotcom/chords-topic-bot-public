package com.example.telegrambot.botapi.handlers.message;

import com.example.telegrambot.botapi.buttons.ButtonsFactory;
import com.example.telegrambot.botapi.handlers.InputMessageHandler;
import com.example.telegrambot.utils.BotState;
import com.example.telegrambot.utils.MessageType;
import com.example.telegrambot.utils.ReplyToUser;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
public class ConversationHandler implements InputMessageHandler {
    @Override
    public SendMessage handle(long chatId, BotState state) {
        SendMessage messageToSend = new SendMessage();
        messageToSend.setChatId(chatId);

        switch (state) {
            case START -> {
                messageToSend.setText(ReplyToUser.START.getTitle());
                ButtonsFactory.addReplyKeyboardMarkup(messageToSend);
            }
            case HELP -> {
                messageToSend.setText(ReplyToUser.HELP.getTitle());
                ButtonsFactory.addReplyKeyboardMarkup(messageToSend);
            }
            default -> {
                messageToSend.setText("Братик, что ты делаешь? Я не могу обработать твоё сообщение :(");
                ButtonsFactory.addReplyKeyboardMarkup(messageToSend);
            }
        }

        return messageToSend;
    }

    @Override
    public MessageType getHandlerName() {
        return MessageType.CONVERSATION;
    }
}
