package ru.floerka.max.core.models.request.messages;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;
import ru.floerka.max.core.api.objects.MaxObject;
import ru.floerka.max.core.api.queries.Param;

@AllArgsConstructor
@Getter
public class GetMessagesRequest extends MaxObject {

    private final @Param(require = false) String chatId;
    private final @Param(require = false) String messageIds;
    private final @Param(require = false) Long from;
    private final @Param(require = false) Long to;
    private final @Param(require = false) Integer count;
}
