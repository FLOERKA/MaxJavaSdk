package ru.floerka.max.core.models.response.chat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;

@AllArgsConstructor
@Getter
public class BotInChatResponse {

    private final long userId;
    private final String firstName;
    private final @Nullable String lastName;
    private final @Nullable String username;
    private final boolean isBot;
}
