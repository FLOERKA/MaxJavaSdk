package ru.floerka.max.core.models.user;

import lombok.Getter;
import ru.floerka.max.core.api.queries.Param;

@Getter
public class UserWithPhoto extends User {

    private final @Param() String description;
    private final @Param() String avatar_utl;
    private final @Param() String fullAvatarUrl;

    public UserWithPhoto(long userId, String firstName, String lastName, String username, boolean isBot, long lastActivityTime, String name, String description, String avatar_utl, String fullAvatarUrl) {
        super(userId, firstName, lastName, username, isBot, lastActivityTime, name);
        this.description = description;
        this.avatar_utl = avatar_utl;
        this.fullAvatarUrl = fullAvatarUrl;
    }
}
