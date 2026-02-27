package ru.floerka.max.core.models.request.messages;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;
import ru.floerka.max.core.api.objects.ApiEndpoint;
import ru.floerka.max.core.api.objects.HttpMethod;
import ru.floerka.max.core.api.objects.MaxObject;
import ru.floerka.max.core.api.objects.MaxRequest;
import ru.floerka.max.core.api.queries.Param;
import ru.floerka.max.core.models.response.messages.EditMessageResponse;
import ru.floerka.max.core.models.response.messages.GetMessagesResponse;

@AllArgsConstructor
@Getter
@ApiEndpoint(path = "messages", method = HttpMethod.POST, response = GetMessagesResponse.class)
public class GetMessagesRequest extends MaxRequest {

    private final @Param(require = false) String chatId;
    private final @Param(require = false) String messageIds;
    private final @Param(require = false) Long from;
    private final @Param(require = false) Long to;
    private final @Param(require = false) Integer count;

    private GetMessagesRequest(Builder builder) {
        chatId = builder.chatId;
        messageIds = builder.messageIds;
        from = builder.from;
        to = builder.to;
        count = builder.count;
    }

    public static final class Builder {
        private String chatId;
        private String messageIds;
        private Long from;
        private Long to;
        private Integer count;

        public Builder() {
        }

        public Builder chatId(String chatId) {
            this.chatId = chatId;
            return this;
        }

        public Builder messageIds(String messageIds) {
            this.messageIds = messageIds;
            return this;
        }

        public Builder from(Long from) {
            this.from = from;
            return this;
        }

        public Builder to(Long to) {
            this.to = to;
            return this;
        }

        public Builder count(Integer count) {
            this.count = count;
            return this;
        }

        public GetMessagesRequest build() {
            return new GetMessagesRequest(this);
        }
    }
}
