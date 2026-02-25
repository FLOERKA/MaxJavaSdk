package ru.floerka.max.core.models.messages.update;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;
import ru.floerka.max.core.api.objects.MaxObject;
import ru.floerka.max.core.api.queries.Param;
import ru.floerka.max.core.models.messages.Message;

import java.util.Locale;

@AllArgsConstructor
@Getter
@Builder
public class Update extends MaxObject {

    private final @Param String updateType;
    private final @Param long timestamp;
    private final @Param Message message;
    private final @Param(require = false) String userLocale;

    public Type getTypeAsEnum() {
        return Type.valueOf(updateType.toUpperCase(Locale.ROOT));
    }


    public enum Type {
        MESSAGE_CREATE,
        MESSAGE_CALLBACK,
        MESSAGE_EDITED,
        MESSAGE_REMOVED,
        BOT_ADDED,
        BOT_REMOVED,
        DIALOG_MUTED,
        DIALOG_UNMUTED,
        DIALOG_CLEARED,
        DIALOG_REMOVED,
        USER_ADDED,
        USER_REMOVED,
        BOT_STARTED,
        BOT_STOPPED,
        CHAT_TITLE_CHANGED
    }
}
