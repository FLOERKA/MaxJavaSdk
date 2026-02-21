package ru.floerka.max.core.models.messages.request.chat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import ru.floerka.max.core.api.objects.MaxObject;
import ru.floerka.max.core.api.queries.Param;
import ru.floerka.max.core.models.messages.enums.actions.SenderAction;

@AllArgsConstructor
@Getter
@Builder
public class ChatActionRequest extends MaxObject {
    private final @Param SenderAction action;
}
