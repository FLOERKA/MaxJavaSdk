package ru.floerka.max.core.models.messages.stat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.floerka.max.core.api.queries.Param;

@AllArgsConstructor
@Getter
public class MessageStat {
    private final @Param int views;
}
