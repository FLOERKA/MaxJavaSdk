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
import ru.floerka.max.core.models.response.chat.ChatMembersResponse;
import ru.floerka.max.core.models.response.chat.ChatResponse;

@AllArgsConstructor
@Getter
@ApiEndpoint(path = "chats/{?}/members/members", method = HttpMethod.POST, response = ChatMembersResponse.class)
public class ChatMembersRequest extends MaxRequest {

    private final @Param(type = ParamType.URL) long chatId;

    private final @Param(require = false, type = ParamType.QUERY) Integer[] userIds;
    private final @Param(require = false, type = ParamType.QUERY) Long marker;
    private final @Param(require = false, type = ParamType.QUERY) Integer count;

    private ChatMembersRequest(Builder builder) {
        chatId = builder.chatId;
        userIds = builder.userIds;
        marker = builder.marker;
        count = builder.count;
    }

    public static final class Builder {
        private long chatId;
        private Integer[] userIds;
        private Long marker;
        private Integer count;

        public Builder() {
        }

        public Builder chatId(long chatId) {
            this.chatId = chatId;
            return this;
        }

        public Builder userIds(Integer[] userIds) {
            this.userIds = userIds;
            return this;
        }

        public Builder marker(Long marker) {
            this.marker = marker;
            return this;
        }

        public Builder count(Integer count) {
            this.count = count;
            return this;
        }

        public ChatMembersRequest build() {
            return new ChatMembersRequest(this);
        }
    }
}

