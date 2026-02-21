package ru.floerka.max.core.models.response.subscriptions;

import ru.floerka.max.core.models.response.chat.ChatResponse;

public class RemoveSubscriptionsResponse extends ChatResponse {
    public RemoveSubscriptionsResponse(boolean success, String message) {
        super(success, message);
    }
}
