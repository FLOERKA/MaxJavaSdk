package ru.floerka.max.core.models.request.messages;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;
import ru.floerka.max.core.api.objects.ApiEndpoint;
import ru.floerka.max.core.api.objects.HttpMethod;
import ru.floerka.max.core.api.objects.MaxObject;
import ru.floerka.max.core.api.queries.Param;
import ru.floerka.max.core.api.queries.ParamType;
import ru.floerka.max.core.models.messages.request.NewMessageBody;
import ru.floerka.max.core.models.response.messages.EditMessageResponse;
import ru.floerka.max.core.models.response.messages.SendMessageResponse;

@AllArgsConstructor
@Getter
@ApiEndpoint(path = "messages", method = HttpMethod.POST, response = SendMessageResponse.class)
public class SendMessageRequest extends MaxObject {

    private final @Param(require = false, type = ParamType.QUERY) Long userId;
    private final @Param(require = false, type = ParamType.QUERY) Long chatId;
    private final @Param(require = false, type = ParamType.QUERY) Boolean disableLinkPreview;

    private final @Param(type = ParamType.BODY) NewMessageBody body;
}
