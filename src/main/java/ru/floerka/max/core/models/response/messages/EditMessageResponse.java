package ru.floerka.max.core.models.response.messages;

import ru.floerka.max.core.models.response.chat.ChatResponse;

public class EditMessageResponse extends ChatResponse {

    public EditMessageResponse(boolean success, String message) {
        super(success, message);
    }
}
