package ru.floerka.max.core.models.user;

import lombok.Getter;
import org.jetbrains.annotations.Nullable;
import ru.floerka.max.core.api.queries.Param;
import ru.floerka.max.core.models.messages.enums.ChatAdminPermission;

@Getter
public class ChatMember extends UserWithPhoto {

    private final @Param Long lastAssessTime;
    private final @Param boolean isOwner;
    private final @Param long joinTime;
    private final @Param(require = false) ChatAdminPermission[] permissions;
    private final @Param(require = false) String alias;

    public ChatMember(long userId, String firstName, String lastName, String username, boolean isBot, long lastActivityTime, String name, String description, String avatar_utl, String fullAvatarUrl
    , Long lastAssessTime, boolean isOwner, long joinTime, ChatAdminPermission[] permissions, String alias) {
        super(userId, firstName, lastName, username, isBot, lastActivityTime, name, description, avatar_utl, fullAvatarUrl);
        this.lastAssessTime = lastAssessTime;
        this.isOwner = isOwner;
        this.joinTime = joinTime;
        this.permissions = permissions;
        this.alias = alias;
    }
}
