package ru.floerka.max.core.models.messages.request.attachment.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.floerka.max.core.api.queries.Param;

@AllArgsConstructor
@Getter
public class StickerPayload extends AttachmentPayload {

    private final @Param String code;
}
