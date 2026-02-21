package ru.floerka.max.core.models.messages.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import ru.floerka.max.core.api.queries.Param;
import ru.floerka.max.core.models.messages.enums.MessageLinkType;

@AllArgsConstructor
@Getter
@Builder
public class NewMessageLink {

    private final @Param MessageLinkType type;
    private final @Param String mid;
}
