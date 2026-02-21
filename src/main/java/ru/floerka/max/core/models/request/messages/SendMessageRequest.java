package ru.floerka.max.core.models.request.messages;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;
import ru.floerka.max.core.api.objects.MaxObject;
import ru.floerka.max.core.api.queries.Param;
import ru.floerka.max.core.models.messages.request.NewMessageBody;

@AllArgsConstructor
@Getter
public class SendMessageRequest extends MaxObject {

    private final @Param(require = false) Long userId;
    private final @Param(require = false) Long chatId;
    private final @Param(require = false) Boolean disableLinkPreview;

    private final @Param NewMessageBody body;
}
