package ru.floerka.max.core.models.messages.request.attachment.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.floerka.max.core.api.queries.Param;

@AllArgsConstructor
@Getter
public class UploadedInfo extends AttachmentPayload {
    private final @Param String token;
}
