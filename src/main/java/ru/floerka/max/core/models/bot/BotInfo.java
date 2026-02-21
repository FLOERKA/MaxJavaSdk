package ru.floerka.max.core.models.bot;

import lombok.Getter;
import org.jetbrains.annotations.Nullable;
import ru.floerka.max.core.api.queries.Param;
import ru.floerka.max.core.models.user.UserWithPhoto;

import java.util.Arrays;
import java.util.List;

@Getter
public class BotInfo extends UserWithPhoto {

    private final @Param(require = false) BotCommand[] commands;

    public BotInfo(long userId, String firstName, String lastName, String username, boolean isBot, long lastActivityTime, String name, String description, String avatar_utl, String fullAvatarUrl, BotCommand[] commands) {
        super(userId, firstName, lastName, username, isBot, lastActivityTime, name, description, avatar_utl, fullAvatarUrl);
        this.commands = commands;
    }

    public List<BotCommand> getCommandsAsList() {
        return Arrays.asList(commands);
    }


}
