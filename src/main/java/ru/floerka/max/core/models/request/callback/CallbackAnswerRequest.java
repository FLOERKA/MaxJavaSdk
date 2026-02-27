package ru.floerka.max.core.models.request.callback;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import ru.floerka.max.core.api.objects.ApiEndpoint;
import ru.floerka.max.core.api.objects.HttpMethod;
import ru.floerka.max.core.api.objects.MaxObject;
import ru.floerka.max.core.api.objects.MaxRequest;
import ru.floerka.max.core.api.queries.Param;
import ru.floerka.max.core.models.response.chat.ChatResponse;

@AllArgsConstructor
@Getter
@ApiEndpoint(path = "callback/answer", method = HttpMethod.POST, response = ChatResponse.class)
public class CallbackAnswerRequest extends MaxRequest {
    private final @Param String callbackId;
    private final @Param String body;

    private CallbackAnswerRequest(Builder builder) {
        callbackId = builder.callbackId;
        body = builder.body;
    }

    public static final class Builder {
        private String callbackId;
        private String body;

        public Builder() {
        }

        public Builder callbackId(String callbackId) {
            this.callbackId = callbackId;
            return this;
        }

        public Builder body(String body) {
            this.body = body;
            return this;
        }

        public CallbackAnswerRequest build() {
            return new CallbackAnswerRequest(this);
        }
    }
}
