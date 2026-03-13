package ru.floerka.max.core.models.messages.request.attachment.payload;

import lombok.Getter;
import ru.floerka.max.core.api.queries.Param;
import ru.floerka.max.core.models.messages.request.attachment.buttons.MessageButton;

import java.util.List;

@Getter
public class InlineKeyboardPayload extends AttachmentPayload {

    private final @Param Button[] buttons;


    public InlineKeyboardPayload(MessageButton... buttons) {
        this.buttons = convert(buttons);
    }

    public InlineKeyboardPayload(List<MessageButton> buttons) {
        this.buttons = convert(buttons);

    }

    private Button[] convert(MessageButton... nonParsedButtons) {
        Button[] array = new Button[nonParsedButtons.length];

        for(int i =0; i < nonParsedButtons.length; i++) {
            MessageButton messageButton = nonParsedButtons[i];
            Button button = new Button(messageButton.getType().name().toLowerCase(), messageButton);
            array[i] = button;
        }
        return array;
    }
    private Button[] convert(List<MessageButton> buttons) {
        MessageButton[] array = new MessageButton[buttons.size()];
        for(int i =0; i < buttons.size(); i++) {
            array[i] = buttons.get(i);
        }
        return convert(array);
    }


    public record Button(@Param String type, @Param MessageButton button) {
    }
}
