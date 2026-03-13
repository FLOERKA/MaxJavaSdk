package ru.floerka.max.core.models.messages.request.attachment.buttons;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.floerka.max.core.api.objects.MaxObject;
import ru.floerka.max.core.api.queries.Param;

@AllArgsConstructor
@Getter
public class MessageButton extends MaxObject {

    private final @Param String text;


    public ButtonType getType() {
        return ButtonType.MESSAGE;
    }
}
