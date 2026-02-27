package ru.floerka.max.core.models.request.chat;

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
@ApiEndpoint(path = "chats/{?}/members/admins/{?}", method = HttpMethod.DELETE, response = ChatResponse.class)
public class ChatDeleteAdminRequest extends MaxRequest {

    private final @Param(type = ParamType.URL) Long chatId;
    private final @Param(type = ParamType.URL) Long userId;

    private ChatDeleteAdminRequest(Builder builder) {
        chatId = builder.chatId;
        userId = builder.userId;
    }

    public static final class Builder {
        private Long chatId;
        private Long userId;

        public Builder() {
        }

        public Builder chatId(Long chatId) {
            this.chatId = chatId;
            return this;
        }

        public Builder userId(Long userId) {
            this.userId = userId;
            return this;
        }

        public ChatDeleteAdminRequest build() {
            return new ChatDeleteAdminRequest(this);
        }
    }
}
