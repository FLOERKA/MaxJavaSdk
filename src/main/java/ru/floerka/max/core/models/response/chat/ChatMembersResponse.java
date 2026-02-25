package ru.floerka.max.core.models.response.chat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;
import ru.floerka.max.core.api.objects.MaxObject;
import ru.floerka.max.core.models.user.ChatMember;

@AllArgsConstructor
@Getter
public class ChatMembersResponse extends MaxObject {

    private final ChatMember[] members;
    private final @Nullable Long marker;

}
