package ru.floerka.max.core.models.request.chat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;
import ru.floerka.max.core.api.objects.ApiEndpoint;
import ru.floerka.max.core.api.objects.HttpMethod;
import ru.floerka.max.core.api.objects.MaxObject;
import ru.floerka.max.core.api.queries.Param;
import ru.floerka.max.core.models.messages.request.PhotoAttachmentRequestPayload;
import ru.floerka.max.core.models.response.chat.ChatResponse;

@AllArgsConstructor
@Getter
@Builder
@ApiEndpoint(path = "chats", method = HttpMethod.POST, response = ChatResponse.class)
public class ChatPatchRequest extends MaxObject {

    private final @Param(require = false) PhotoAttachmentRequestPayload icon;
    private final @Param(require = false) String title;
    private final @Param(require = false) String pin;
    private final @Param(require = false) Boolean notify;

}
