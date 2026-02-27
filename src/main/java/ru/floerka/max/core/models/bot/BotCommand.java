package ru.floerka.max.core.models.bot;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.floerka.max.core.api.objects.MaxObject;
import ru.floerka.max.core.api.queries.Param;

@AllArgsConstructor
@Getter
public class BotCommand extends MaxObject {

    private final @Param String name;
    private final @Param String description;
}
