package ru.floerka.max.core.models.messages.request.attachment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.floerka.max.core.api.queries.Param;
import ru.floerka.max.core.models.messages.request.attachment.payload.AttachmentPayload;

@AllArgsConstructor
@Getter
public class AttachmentRequest {

    private final @Param(require = false) String type;
    private final @Param AttachmentPayload payload;


    public AttachmentRequest(Type type, AttachmentPayload payload) {
        this.type = type.name().toLowerCase();
        this.payload = payload;
    }


    public enum Type {
        IMAGE,VIDEO,AUDIO,FILE,STICKER,CONTACT,INLINE_KEYBOARD,SHARE,LOCATION
    }
}
