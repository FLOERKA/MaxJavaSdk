package ru.floerka.max.core.models.messages;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.floerka.max.core.api.queries.Param;
import ru.floerka.max.core.models.messages.enums.ChatType;

@AllArgsConstructor
@Getter
public class Recipient {

    private final @Param long chatId;
    private final @Param ChatType chatType;
    private final @Param long userId;
}
