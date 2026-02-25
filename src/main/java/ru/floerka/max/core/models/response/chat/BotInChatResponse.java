package ru.floerka.max.core.models.response.chat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;
import ru.floerka.max.core.api.objects.MaxObject;

@AllArgsConstructor
@Getter
public class BotInChatResponse extends MaxObject {

    private final long userId;
    private final String firstName;
    private final @Nullable String lastName;
    private final @Nullable String username;
    private final boolean isBot;
}
