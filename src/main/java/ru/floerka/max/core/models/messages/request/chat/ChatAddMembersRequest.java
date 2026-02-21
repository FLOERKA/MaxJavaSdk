package ru.floerka.max.core.models.messages.request.chat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import ru.floerka.max.core.api.objects.MaxObject;
import ru.floerka.max.core.api.queries.Param;

@AllArgsConstructor
@Getter
@Builder
public class ChatAddMembersRequest extends MaxObject {

    private final @Param Integer[] userIds;
}
