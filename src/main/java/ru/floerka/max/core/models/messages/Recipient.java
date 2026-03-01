package ru.floerka.max.core.models.messages;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import ru.floerka.max.core.api.objects.MaxObject;
import ru.floerka.max.core.api.queries.Param;
import ru.floerka.max.core.models.messages.enums.ChatType;

@AllArgsConstructor
@Getter
public class Recipient extends MaxObject {

    private final @Param long chatId;
    private final @Param ChatType chatType;
    private final @Param long userId;

    private Recipient(Builder builder) {
        chatId = builder.chatId;
        chatType = builder.chatType;
        userId = builder.userId;
    }

    public static final class Builder {
        private long chatId;
        private ChatType chatType;
        private long userId;

        public Builder() {
        }

        public Builder chatId(long chatId) {
            this.chatId = chatId;
            return this;
        }

        public Builder chatType(ChatType chatType) {
            this.chatType = chatType;
            return this;
        }

        public Builder userId(long userId) {
            this.userId = userId;
            return this;
        }

        public Recipient build() {
            return new Recipient(this);
        }
    }
}
