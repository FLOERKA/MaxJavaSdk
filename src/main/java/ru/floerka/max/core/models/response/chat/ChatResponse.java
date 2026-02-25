package ru.floerka.max.core.models.response.chat;

import lombok.Getter;
import ru.floerka.max.core.api.objects.MaxObject;

@Getter
public class ChatResponse extends MaxObject {

    private final boolean success;
    private final String message;

    public ChatResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

}
