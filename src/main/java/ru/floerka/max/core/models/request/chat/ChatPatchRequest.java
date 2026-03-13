package ru.floerka.max.core.models.request.chat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.floerka.max.core.api.objects.ApiEndpoint;
import ru.floerka.max.core.api.objects.HttpMethod;
import ru.floerka.max.core.api.objects.MaxRequest;
import ru.floerka.max.core.api.queries.Param;
import ru.floerka.max.core.api.queries.ParamType;
import ru.floerka.max.core.models.messages.request.attachment.payload.PhotoPayload;
import ru.floerka.max.core.models.response.chat.ChatResponse;

@AllArgsConstructor
@Getter
@ApiEndpoint(path = "chats", method = HttpMethod.PATCH, response = ChatResponse.class)
public class ChatPatchRequest extends MaxRequest {

    private final @Param(type = ParamType.URL) Long chatId;
    private final @Param(require = false) PhotoPayload icon;
    private final @Param(require = false) String title;
    private final @Param(require = false) String pin;
    private final @Param(require = false) Boolean notify;

    private ChatPatchRequest(Builder builder) {
        chatId = builder.chatId;
        icon = builder.icon;
        title = builder.title;
        pin = builder.pin;
        notify = builder.notify;
    }

    public static final class Builder {
        private Long chatId;
        private PhotoPayload icon;
        private String title;
        private String pin;
        private Boolean notify;

        public Builder() {
        }

        public Builder chatId(Long chatId) {
            this.chatId = chatId;
            return this;
        }

        public Builder icon(PhotoPayload icon) {
            this.icon = icon;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder pin(String pin) {
            this.pin = pin;
            return this;
        }

        public Builder notify(Boolean notify) {
            this.notify = notify;
            return this;
        }

        public ChatPatchRequest build() {
            return new ChatPatchRequest(this);
        }
    }
}
