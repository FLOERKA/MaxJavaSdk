package ru.floerka.max.core.models.response.chat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.floerka.max.core.models.user.ChatMember;

@AllArgsConstructor
@Getter
public class ChatAdminsResponse {

    private final ChatMember[] members;
    private final Long marker;
}
