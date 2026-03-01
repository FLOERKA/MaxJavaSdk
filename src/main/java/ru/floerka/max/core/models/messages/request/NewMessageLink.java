package ru.floerka.max.core.models.messages.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import ru.floerka.max.core.api.objects.MaxObject;
import ru.floerka.max.core.api.queries.Param;
import ru.floerka.max.core.models.messages.enums.MessageLinkType;

@AllArgsConstructor
@Getter
public class NewMessageLink extends MaxObject {

    private final @Param MessageLinkType type;
    private final @Param String mid;

    private NewMessageLink(Builder builder) {
        type = builder.type;
        mid = builder.mid;
    }

    public static final class Builder {
        private MessageLinkType type;
        private String mid;

        public Builder() {
        }

        public Builder type(MessageLinkType type) {
            this.type = type;
            return this;
        }

        public Builder mid(String mid) {
            this.mid = mid;
            return this;
        }

        public NewMessageLink build() {
            return new NewMessageLink(this);
        }
    }
}
