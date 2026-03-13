package ru.floerka.max.core.models.messages.request.attachment.buttons;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.floerka.max.core.api.queries.Param;

@Getter
public class RequestGeoButton extends MessageButton {

    private final @Param Boolean quick;
    public RequestGeoButton(String text, Boolean quick) {
        super(text);
        this.quick = quick;
    }
    public ButtonType getType() {
        return ButtonType.REQUEST_GEO_LOCATION;
    }
}
