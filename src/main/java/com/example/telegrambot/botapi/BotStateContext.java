package com.example.telegrambot.botapi;

import com.example.telegrambot.botapi.handlers.InputMessageHandler;
import com.example.telegrambot.utils.BotState;
import com.example.telegrambot.utils.MessageType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class BotStateContext {
    private final Map<MessageType, InputMessageHandler> messageHandlers = new HashMap<>();

    public BotStateContext(List<InputMessageHandler> messageHandlers) {
        messageHandlers.forEach(handler -> this.messageHandlers.put(handler.getHandlerName(), handler));
    }

    public SendMessage processInputMessage(BotState currentState, Message message) {
        InputMessageHandler currentMessageHandler = findMessageHandler(currentState);
        return currentMessageHandler.handle(message.getChatId(), currentState);
    }

    private InputMessageHandler findMessageHandler(BotState currentState) {
        if (isConversationMessageType(currentState)) {
            return messageHandlers.get(MessageType.CONVERSATION);
        }

        if (isTopicMessageType(currentState)) {
            return messageHandlers.get(MessageType.TOPIC);
        }
        log.error(String.format("Not found handler for type '%s'", currentState.name()));
        return null;
    }

    private boolean isConversationMessageType(BotState currentState) {
        return switch (currentState) {
            case START, HELP, ERROR -> true;
            default -> false;
        };
    }

    private boolean isTopicMessageType(BotState currentState) {
        return switch (currentState) {
            case TODAY, WEEK, MONTH, ALL -> true;
            default -> false;
        };
    }
}
