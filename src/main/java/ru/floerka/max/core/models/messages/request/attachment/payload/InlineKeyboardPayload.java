package ru.floerka.max.core.models.messages.request.attachment.payload;

import lombok.Getter;
import ru.floerka.max.core.api.queries.Param;
import ru.floerka.max.core.models.messages.request.attachment.buttons.MessageButton;

import java.util.List;

@Getter
public class InlineKeyboardPayload extends AttachmentPayload {

    private final @Param Button[] buttons;


    @SafeVarargs
    public <T extends MessageButton> InlineKeyboardPayload(T... buttons) {
        this.buttons = convert(buttons);
    }

    public <T extends MessageButton> InlineKeyboardPayload(List<T> buttons) {
        this.buttons = convert(buttons);
    }

    @SafeVarargs
    private <T extends MessageButton> Button[] convert(T... nonParsedButtons) {
        Button[] array = new Button[nonParsedButtons.length];

        for(int i =0; i < nonParsedButtons.length; i++) {
            MessageButton messageButton = nonParsedButtons[i];
            Button button = new Button(messageButton.getType().name().toLowerCase(), messageButton);
            array[i] = button;
        }
        return array;
    }

    private <T extends MessageButton> Button[] convert(List<T> buttons) {
        return buttons.stream()
                .map(mb -> new Button(mb.getType().name().toLowerCase(), mb))
                .toArray(Button[]::new);
    }


    public record Button(@Param String type, @Param MessageButton button) {
    }
}
