package ru.floerka.max.core.models.request.messages;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import ru.floerka.max.core.api.objects.ApiEndpoint;
import ru.floerka.max.core.api.objects.HttpMethod;
import ru.floerka.max.core.api.objects.MaxObject;
import ru.floerka.max.core.api.queries.Param;
import ru.floerka.max.core.models.messages.request.NewMessageBody;

@AllArgsConstructor
@Getter
@Builder
public class EditMessageRequest extends MaxObject {

    private final @Param String messageId;

    private final @Param NewMessageBody body;
}
