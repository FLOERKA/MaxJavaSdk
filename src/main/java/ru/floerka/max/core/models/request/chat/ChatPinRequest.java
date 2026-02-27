package ru.floerka.max.core.models.request.chat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;
import ru.floerka.max.core.api.objects.ApiEndpoint;
import ru.floerka.max.core.api.objects.HttpMethod;
import ru.floerka.max.core.api.objects.MaxObject;
import ru.floerka.max.core.api.objects.MaxRequest;
import ru.floerka.max.core.api.queries.Param;
import ru.floerka.max.core.api.queries.ParamType;
import ru.floerka.max.core.models.response.chat.ChatPinResponse;
import ru.floerka.max.core.models.response.chat.ChatResponse;

@AllArgsConstructor
@Getter
@ApiEndpoint(path = "chats/{?}/pin", method = HttpMethod.POST, response = ChatPinResponse.class)
public class ChatPinRequest extends MaxRequest {

    private final @Param(type = ParamType.URL) Long chatId;

    private final @Param String messageId;
    private final @Param(require = false) Boolean notify;

    private ChatPinRequest(Builder builder) {
        chatId = builder.chatId;
        messageId = builder.messageId;
        notify = builder.notify;
    }

    public static final class Builder {
        private Long chatId;
        private String messageId;
        private Boolean notify;

        public Builder() {
        }

        public Builder chatId(Long chatId) {
            this.chatId = chatId;
            return this;
        }

        public Builder messageId(String messageId) {
            this.messageId = messageId;
            return this;
        }

        public Builder notify(Boolean notify) {
            this.notify = notify;
            return this;
        }

        public ChatPinRequest build() {
            return new ChatPinRequest(this);
        }
    }
}
