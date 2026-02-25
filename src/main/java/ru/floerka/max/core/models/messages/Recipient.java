package ru.floerka.max.core.models.messages;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import ru.floerka.max.core.api.objects.MaxObject;
import ru.floerka.max.core.api.queries.Param;
import ru.floerka.max.core.models.messages.enums.ChatType;

@AllArgsConstructor
@Getter
@Builder
public class Recipient extends MaxObject {

    private final @Param long chatId;
    private final @Param ChatType chatType;
    private final @Param long userId;
}
