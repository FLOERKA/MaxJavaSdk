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
public class Update extends MaxObject {

    private final @Param String updateType;
    private final @Param long timestamp;
    private final @Param Message message;
    private final @Param(require = false) String userLocale;

    private Update(Builder builder) {
        updateType = builder.updateType;
        timestamp = builder.timestamp;
        message = builder.message;
        userLocale = builder.userLocale;
    }

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

    public static final class Builder {
        private String updateType;
        private long timestamp;
        private Message message;
        private String userLocale;

        public Builder() {
        }

        public Builder updateType(String updateType) {
            this.updateType = updateType;
            return this;
        }

        public Builder timestamp(long timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder message(Message message) {
            this.message = message;
            return this;
        }

        public Builder userLocale(String userLocale) {
            this.userLocale = userLocale;
            return this;
        }

        public Update build() {
            return new Update(this);
        }
    }
}
