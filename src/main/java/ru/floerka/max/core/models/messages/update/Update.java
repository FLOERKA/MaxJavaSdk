package ru.floerka.max.core.models.messages.update;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;
import ru.floerka.max.core.api.objects.MaxObject;
import ru.floerka.max.core.api.queries.Param;
import ru.floerka.max.core.models.messages.Message;
import ru.floerka.max.core.models.messages.callback.Callback;
import ru.floerka.max.core.models.user.User;

import java.util.Locale;

@AllArgsConstructor
@Getter
public class Update extends MaxObject {

    private final @Param String updateType;
    private final @Param long timestamp;
    private final @Param Message message;
    private final @Param(require = false) String userLocale;

    private final @Param(require = false) Callback callback;
    private final @Param(require = false) Integer messageId;
    private final @Param(require = false) Integer chatId;
    private final @Param(require = false) Integer userId;
    private final @Param(require = false) User user;
    private final @Param(require = false) Boolean isChannel;
    private final @Param(require = false) Long mutedUntil;
    private final @Param(require = false) Long inviterId;
    private final @Param(require = false) Long adminId;
    private final @Param(require = false) String payload;
    private final @Param(require = false) String title;

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

    public boolean hasCallback() {
        return callback != null;
    }
}
