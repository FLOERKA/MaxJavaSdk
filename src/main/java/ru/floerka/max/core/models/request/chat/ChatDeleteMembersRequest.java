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
@ApiEndpoint(path = "chats/{?}/members", method = HttpMethod.DELETE, response = ChatResponse.class)
public class ChatDeleteMembersRequest extends MaxRequest {

    private final @Param(type = ParamType.URL) Long chatId;

    private final @Param(type = ParamType.QUERY) Long userId;
    private final @Param(type = ParamType.QUERY, require = false) Boolean block;

    private ChatDeleteMembersRequest(Builder builder) {
        chatId = builder.chatId;
        userId = builder.userId;
        block = builder.block;
    }

    public static final class Builder {
        private Long chatId;
        private Long userId;
        private Boolean block;

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

        public Builder block(Boolean block) {
            this.block = block;
            return this;
        }

        public ChatDeleteMembersRequest build() {
            return new ChatDeleteMembersRequest(this);
        }
    }
}
