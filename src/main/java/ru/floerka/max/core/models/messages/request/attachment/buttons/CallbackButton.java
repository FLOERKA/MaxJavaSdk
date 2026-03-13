package ru.floerka.max.core.models.messages.request.attachment.buttons;

import lombok.Getter;

@Getter
public class CallbackButton extends MessageButton {

    private final String payload;

    public CallbackButton(String text, String payload) {
        super(text);
        this.payload = payload;
    }
    public ButtonType getType() {
        return ButtonType.CALLBACK;
    }
}
