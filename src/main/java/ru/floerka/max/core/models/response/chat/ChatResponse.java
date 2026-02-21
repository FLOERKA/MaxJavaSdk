package ru.floerka.max.core.models.response.chat;

public class ChatResponse {

    private final boolean success;
    private final String message;

    public ChatResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
