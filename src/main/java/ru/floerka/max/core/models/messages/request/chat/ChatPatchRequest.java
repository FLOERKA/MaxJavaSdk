package ru.floerka.max.core.models.messages.request.chat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;
import ru.floerka.max.core.api.objects.MaxObject;
import ru.floerka.max.core.api.queries.Param;
import ru.floerka.max.core.models.messages.request.PhotoAttachmentRequestPayload;

@AllArgsConstructor
@Getter
@Builder
public class ChatPatchRequest extends MaxObject {

    private final @Param(require = false) PhotoAttachmentRequestPayload icon;
    private final @Param(require = false) String title;
    private final @Param(require = false) String pin;
    private final @Param(require = false) Boolean notify;

}
