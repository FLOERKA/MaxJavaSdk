package ru.floerka.max.core.models.messages.request.attachment.buttons;

import lombok.Getter;
import ru.floerka.max.core.api.queries.Param;

@Getter
public class OpenAppButton extends CallbackButton {
    private final @Param String webApp;
    private final @Param Long contactId;

    public OpenAppButton(String text, String payload, String webApp, long contactId) {
        super(text, payload);
        this.webApp = webApp;
        this.contactId = contactId;
    }
    public ButtonType getType() {
        return ButtonType.OPEN_APP;
    }
}
