package ru.floerka.max.core.models.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;
import ru.floerka.max.core.api.objects.MaxObject;
import ru.floerka.max.core.api.queries.Param;
import ru.floerka.max.core.models.messages.enums.ChatAdminPermission;

@AllArgsConstructor
@Getter
public class ChatAdmin extends MaxObject {

    private final @Param long userId;
    private final @Param ChatAdminPermission[] permissions;
    private final @Param(require = false) String alias;
}
