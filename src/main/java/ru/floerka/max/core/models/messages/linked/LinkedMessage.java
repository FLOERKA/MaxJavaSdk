package ru.floerka.max.core.models.messages.linked;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.floerka.max.core.api.queries.Param;
import ru.floerka.max.core.models.user.User;
import ru.floerka.max.core.models.messages.body.MessageBody;
import ru.floerka.max.core.models.messages.enums.MessageLinkType;

@AllArgsConstructor
@Getter
public class LinkedMessage {

    private final @Param MessageLinkType type;
    private final @Param User sender;
    private final @Param long chatId;
    private final @Param MessageBody message;

}
