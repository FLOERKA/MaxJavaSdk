package ru.floerka.max.core.models.request.chat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import ru.floerka.max.core.api.objects.ApiEndpoint;
import ru.floerka.max.core.api.objects.HttpMethod;
import ru.floerka.max.core.api.objects.MaxObject;
import ru.floerka.max.core.api.queries.Param;
import ru.floerka.max.core.models.messages.enums.actions.SenderAction;
import ru.floerka.max.core.models.response.chat.ChatActionResponse;
import ru.floerka.max.core.models.response.chat.ChatResponse;
import ru.floerka.max.core.models.response.subscriptions.UpdatesResponse;

@AllArgsConstructor
@Getter
@Builder
@ApiEndpoint(path = "chats", method = HttpMethod.POST, response = ChatActionResponse.class)
public class ChatActionRequest extends MaxObject {
    private final @Param SenderAction action;
}
