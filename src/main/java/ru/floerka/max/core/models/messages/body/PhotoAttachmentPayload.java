package ru.floerka.max.core.models.messages.body;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.floerka.max.core.api.queries.Param;

@AllArgsConstructor
@Getter
public class PhotoAttachmentPayload {

    private final @Param long photoId;
    private final @Param String token;
    private final @Param String url;
}
