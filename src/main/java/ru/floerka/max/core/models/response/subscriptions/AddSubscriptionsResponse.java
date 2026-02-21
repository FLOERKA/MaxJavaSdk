package ru.floerka.max.core.models.response.subscriptions;

import ru.floerka.max.core.models.response.chat.ChatResponse;

public class AddSubscriptionsResponse extends ChatResponse {
    public AddSubscriptionsResponse(boolean success, String message) {
        super(success, message);
    }
}
