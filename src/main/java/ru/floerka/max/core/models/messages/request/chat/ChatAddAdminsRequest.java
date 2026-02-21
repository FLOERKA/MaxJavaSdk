package ru.floerka.max.core.models.messages.request.chat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;
import ru.floerka.max.core.api.objects.MaxObject;
import ru.floerka.max.core.api.queries.Param;
import ru.floerka.max.core.models.user.ChatAdmin;

@AllArgsConstructor
@Getter
@Builder
public class ChatAddAdminsRequest extends MaxObject {

    private final @Param ChatAdmin[] admins;
    private final @Param(require = false) Long marker;
}
