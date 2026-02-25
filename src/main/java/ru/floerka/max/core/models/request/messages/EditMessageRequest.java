package ru.floerka.max.core.models.request.messages;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import ru.floerka.max.core.api.objects.ApiEndpoint;
import ru.floerka.max.core.api.objects.HttpMethod;
import ru.floerka.max.core.api.objects.MaxObject;
import ru.floerka.max.core.api.queries.Param;
import ru.floerka.max.core.api.queries.ParamType;
import ru.floerka.max.core.models.messages.request.NewMessageBody;
import ru.floerka.max.core.models.response.chat.ChatResponse;
import ru.floerka.max.core.models.response.messages.EditMessageResponse;

@AllArgsConstructor
@Getter
@Builder
@ApiEndpoint(path = "messages", method = HttpMethod.POST, response = EditMessageResponse.class)
public class EditMessageRequest extends MaxObject {

    private final @Param(type = ParamType.QUERY) String messageId;

    private final @Param(type = ParamType.BODY) NewMessageBody body;
}
