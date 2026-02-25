package ru.floerka.max.core.models.request.chat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;
import ru.floerka.max.core.api.objects.ApiEndpoint;
import ru.floerka.max.core.api.objects.HttpMethod;
import ru.floerka.max.core.api.objects.MaxObject;
import ru.floerka.max.core.api.queries.Param;
import ru.floerka.max.core.models.response.chat.ChatMembersResponse;
import ru.floerka.max.core.models.response.chat.ChatResponse;

@AllArgsConstructor
@Getter
@Builder
@ApiEndpoint(path = "chats", method = HttpMethod.POST, response = ChatMembersResponse.class)
public class ChatMembersRequest extends MaxObject {

    private final @Param long chatId;
    private final @Param(require = false) Integer[] userIds;
    private final @Param(require = false) Long marker;
    private final @Param(require = false) Integer count;

}
