package ru.floerka.max.core.models.messages.linked;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import ru.floerka.max.core.api.objects.MaxObject;
import ru.floerka.max.core.api.queries.Param;
import ru.floerka.max.core.models.user.User;
import ru.floerka.max.core.models.messages.body.MessageBody;
import ru.floerka.max.core.models.messages.enums.MessageLinkType;

@AllArgsConstructor
@Getter
@Builder
public class LinkedMessage extends MaxObject {

    private final @Param MessageLinkType type;
    private final @Param User sender;
    private final @Param long chatId;
    private final @Param MessageBody message;

}
