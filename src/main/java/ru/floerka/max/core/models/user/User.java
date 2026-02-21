package ru.floerka.max.core.models.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import ru.floerka.max.core.api.objects.MaxObject;
import ru.floerka.max.core.api.queries.Param;

@AllArgsConstructor
@Builder
@Getter
public class User extends MaxObject {

    private final @Param() long userId;
    private final @Param() String firstName;
    private final @Param(require = false) String lastName;
    private final @Param(require = false) String username;
    private final @Param() boolean isBot;
    private final @Param() long lastActivityTime;
    private final @Param String name;

}
