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
import ru.floerka.max.core.models.response.chat.ChatActionResponse;
import ru.floerka.max.core.models.response.chat.ChatResponse;
import ru.floerka.max.core.models.user.ChatAdmin;

@AllArgsConstructor
@Getter
@ApiEndpoint(path = "chats/{?}/members/admins", method = HttpMethod.POST, response = ChatResponse.class)
public class ChatAddAdminsRequest extends MaxRequest {

    private final @Param(type = ParamType.URL) Long chatId;

    private final @Param ChatAdmin[] admins;
    private final @Param(require = false) Long marker;

    private ChatAddAdminsRequest(Builder builder) {
        chatId = builder.chatId;
        admins = builder.admins;
        marker = builder.marker;
    }

    public static final class Builder {
        private Long chatId;
        private ChatAdmin[] admins;
        private Long marker;

        public Builder() {
        }

        public Builder chatId(Long chatId) {
            this.chatId = chatId;
            return this;
        }

        public Builder admins(ChatAdmin[] admins) {
            this.admins = admins;
            return this;
        }

        public Builder marker(Long marker) {
            this.marker = marker;
            return this;
        }

        public ChatAddAdminsRequest build() {
            return new ChatAddAdminsRequest(this);
        }
    }
}
