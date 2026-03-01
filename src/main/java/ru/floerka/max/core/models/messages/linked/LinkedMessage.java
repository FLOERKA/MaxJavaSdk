package ru.floerka.max.core.models.messages.linked;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import ru.floerka.max.core.api.objects.MaxObject;
import ru.floerka.max.core.api.queries.Param;
import ru.floerka.max.core.models.user.User;
import ru.floerka.max.core.models.messages.body.MessageBody;
import ru.floerka.max.core.models.messages.enums.MessageLinkType;

@AllArgsConstructor
@Getter
public class LinkedMessage extends MaxObject {

    private final @Param MessageLinkType type;
    private final @Param User sender;
    private final @Param long chatId;
    private final @Param MessageBody message;

    private LinkedMessage(Builder builder) {
        type = builder.type;
        sender = builder.sender;
        chatId = builder.chatId;
        message = builder.message;
    }

    public static final class Builder {
        private MessageLinkType type;
        private User sender;
        private long chatId;
        private MessageBody message;

        public Builder() {
        }

        public Builder type(MessageLinkType type) {
            this.type = type;
            return this;
        }

        public Builder sender(User sender) {
            this.sender = sender;
            return this;
        }

        public Builder chatId(long chatId) {
            this.chatId = chatId;
            return this;
        }

        public Builder message(MessageBody message) {
            this.message = message;
            return this;
        }

        public LinkedMessage build() {
            return new LinkedMessage(this);
        }
    }
}
