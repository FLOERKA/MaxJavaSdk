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
public class ChatMembersRequest extends MaxObject {

    private final @Param long chatId;
    private final @Param(require = false) Integer[] userIds;
    private final @Param(require = false) Long marker;
    private final @Param(require = false) Integer count;

}
