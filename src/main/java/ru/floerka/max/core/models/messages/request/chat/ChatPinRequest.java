package ru.floerka.max.core.models.messages.request.chat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;
import ru.floerka.max.core.api.objects.MaxObject;
import ru.floerka.max.core.api.queries.Param;

@AllArgsConstructor
@Getter
@Builder
public class ChatPinRequest extends MaxObject {

    private final @Param String messageId;
    private final @Param(require = false) Boolean notify;
}
