package ru.floerka.max.core.models.messages.callback;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.floerka.max.core.api.objects.MaxObject;
import ru.floerka.max.core.api.queries.Param;
import ru.floerka.max.core.models.user.User;

@AllArgsConstructor
@Getter
public class Callback extends MaxObject {

    private final @Param Long timestamp;
    private final @Param String callbackId;
    private final @Param(require = false) String payload;
    private final @Param User user;
}
