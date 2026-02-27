package ru.floerka.max.core.models.request.messages;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import ru.floerka.max.core.api.objects.ApiEndpoint;
import ru.floerka.max.core.api.objects.HttpMethod;
import ru.floerka.max.core.api.objects.MaxObject;
import ru.floerka.max.core.api.objects.MaxRequest;
import ru.floerka.max.core.api.queries.Param;
import ru.floerka.max.core.api.queries.ParamType;
import ru.floerka.max.core.models.response.chat.ChatResponse;

@AllArgsConstructor
@Getter
@ApiEndpoint(path = "messages", method = HttpMethod.DELETE, response = ChatResponse.class)
public class DeleteMessageRequest extends MaxRequest {
    private final @Param(type = ParamType.QUERY) Long messageId;

    private DeleteMessageRequest(Builder builder) {
        messageId = builder.messageId;
    }

    public static final class Builder {
        private Long messageId;

        public Builder() {
        }

        public Builder messageId(Long messageId) {
            this.messageId = messageId;
            return this;
        }

        public DeleteMessageRequest build() {
            return new DeleteMessageRequest(this);
        }
    }
}
