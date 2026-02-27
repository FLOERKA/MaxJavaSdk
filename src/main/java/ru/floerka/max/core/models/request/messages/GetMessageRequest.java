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
import ru.floerka.max.core.models.messages.Message;


@AllArgsConstructor
@Getter
@ApiEndpoint(path = "messages", method = HttpMethod.GET, response = Message.class)
public class GetMessageRequest extends MaxRequest {

    private final @Param(type = ParamType.QUERY) String messageId;

    private GetMessageRequest(Builder builder) {
        messageId = builder.messageId;
    }

    public static final class Builder {
        private String messageId;

        public Builder() {
        }

        public Builder messageId(String messageId) {
            this.messageId = messageId;
            return this;
        }

        public GetMessageRequest build() {
            return new GetMessageRequest(this);
        }
    }
}
