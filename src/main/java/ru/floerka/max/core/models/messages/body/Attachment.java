package ru.floerka.max.core.models.messages.body;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import ru.floerka.max.core.api.objects.MaxObject;
import ru.floerka.max.core.api.queries.Param;

@AllArgsConstructor
@Builder
@Getter
public class Attachment extends MaxObject {

    private final @Param String type;
    private final @Param PhotoAttachmentPayload payload;



    public Type getTypeAsEnum() {
        return Type.valueOf(type);
    }
    public enum Type {
        IMAGE,VIDEO,AUDIO,FILE,STICKER,CONTACT,INLINE_KEYBOARD,SHARE,LOCATION
    }
}
