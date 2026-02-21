package ru.floerka.max.core.models.response.chat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.floerka.max.core.models.messages.chats.Chat;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@Getter
public class ChatsGetResponse {

    private final Chat[] chats;
    private final Long marker;


    public List<Chat> chatsAsList() {
        return Arrays.asList(chats);
    }
}
