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
import ru.floerka.max.core.models.user.ChatMember;

@AllArgsConstructor
@Getter
@ApiEndpoint(path = "chats/{?}/members/me", method = HttpMethod.GET, response = ChatMember.class)
public class ChatInInfoRequest extends MaxRequest {

    private final @Param(type = ParamType.URL) Long chatId;

    private ChatInInfoRequest(Builder builder) {
        chatId = builder.chatId;
    }

    public static final class Builder {
        private Long chatId;

        public Builder() {
        }

        public Builder chatId(Long chatId) {
            this.chatId = chatId;
            return this;
        }

        public ChatInInfoRequest build() {
            return new ChatInInfoRequest(this);
        }
    }
}
