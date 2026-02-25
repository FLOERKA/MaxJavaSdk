package ru.floerka.max.core.models.request.messages;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;
import ru.floerka.max.core.api.objects.ApiEndpoint;
import ru.floerka.max.core.api.objects.HttpMethod;
import ru.floerka.max.core.api.objects.MaxObject;
import ru.floerka.max.core.api.queries.Param;
import ru.floerka.max.core.models.response.messages.EditMessageResponse;
import ru.floerka.max.core.models.response.messages.GetMessagesResponse;

@AllArgsConstructor
@Getter
@ApiEndpoint(path = "messages", method = HttpMethod.POST, response = GetMessagesResponse.class)
public class GetMessagesRequest extends MaxObject {

    private final @Param(require = false) String chatId;
    private final @Param(require = false) String messageIds;
    private final @Param(require = false) Long from;
    private final @Param(require = false) Long to;
    private final @Param(require = false) Integer count;
}
