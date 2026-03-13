package ru.floerka.max.core.models.messages.request.attachment.buttons;

import lombok.Getter;
import ru.floerka.max.core.api.queries.Param;

@Getter
public class LinkButton extends MessageButton {

    private final @Param String url;

    public LinkButton(String text, String url) {
        super(text);
        this.url = url;
    }

    public ButtonType getType() {
        return ButtonType.LINK;
    }
}
