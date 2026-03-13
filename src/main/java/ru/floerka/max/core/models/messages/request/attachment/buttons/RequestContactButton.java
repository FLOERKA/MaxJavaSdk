package ru.floerka.max.core.models.messages.request.attachment.buttons;

public class RequestContactButton extends MessageButton{
    public RequestContactButton(String text) {
        super(text);
    }
    public ButtonType getType() {
        return ButtonType.REQUEST_CONTACT;
    }
}
