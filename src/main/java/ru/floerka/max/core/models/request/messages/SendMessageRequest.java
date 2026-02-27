package ru.floerka.max.core.models.request.messages;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;
import ru.floerka.max.core.api.objects.ApiEndpoint;
import ru.floerka.max.core.api.objects.HttpMethod;
import ru.floerka.max.core.api.objects.MaxObject;
import ru.floerka.max.core.api.objects.MaxRequest;
import ru.floerka.max.core.api.queries.Param;
import ru.floerka.max.core.api.queries.ParamType;
import ru.floerka.max.core.models.messages.request.NewMessageBody;
import ru.floerka.max.core.models.response.messages.EditMessageResponse;
import ru.floerka.max.core.models.response.messages.SendMessageResponse;

@AllArgsConstructor
@Getter
@ApiEndpoint(path = "messages", method = HttpMethod.POST, response = SendMessageResponse.class)
public class SendMessageRequest extends MaxRequest {

    private final @Param(require = false, type = ParamType.QUERY) Long userId;
    private final @Param(require = false, type = ParamType.QUERY) Long chatId;
    private final @Param(require = false, type = ParamType.QUERY) Boolean disableLinkPreview;

    private final @Param(type = ParamType.BODY) NewMessageBody body;

    private SendMessageRequest(Builder builder) {
        userId = builder.userId;
        chatId = builder.chatId;
        disableLinkPreview = builder.disableLinkPreview;
        body = builder.body;
    }

    public static final class Builder {
        private Long userId;
        private Long chatId;
        private Boolean disableLinkPreview;
        private NewMessageBody body;

        public Builder() {
        }

        public Builder userId(Long userId) {
            this.userId = userId;
            return this;
        }

        public Builder chatId(Long chatId) {
            this.chatId = chatId;
            return this;
        }

        public Builder disableLinkPreview(Boolean disableLinkPreview) {
            this.disableLinkPreview = disableLinkPreview;
            return this;
        }

        public Builder body(NewMessageBody body) {
            this.body = body;
            return this;
        }

        public SendMessageRequest build() {
            return new SendMessageRequest(this);
        }
    }
}
