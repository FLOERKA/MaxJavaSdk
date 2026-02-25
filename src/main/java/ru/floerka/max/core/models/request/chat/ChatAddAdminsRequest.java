package ru.floerka.max.core.models.request.chat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;
import ru.floerka.max.core.api.objects.ApiEndpoint;
import ru.floerka.max.core.api.objects.HttpMethod;
import ru.floerka.max.core.api.objects.MaxObject;
import ru.floerka.max.core.api.queries.Param;
import ru.floerka.max.core.models.response.chat.ChatActionResponse;
import ru.floerka.max.core.models.response.chat.ChatResponse;
import ru.floerka.max.core.models.user.ChatAdmin;

@AllArgsConstructor
@Getter
@Builder
@ApiEndpoint(path = "chats", method = HttpMethod.POST, response = ChatResponse.class)
public class ChatAddAdminsRequest extends MaxObject {

    private final @Param ChatAdmin[] admins;
    private final @Param(require = false) Long marker;
}
