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
import ru.floerka.max.core.models.response.chat.ChatPinResponse;
import ru.floerka.max.core.models.response.chat.ChatsGetResponse;

@AllArgsConstructor
@Getter
@ApiEndpoint(path = "chats", method = HttpMethod.POST, response = ChatsGetResponse.class)
public class ChatsGetRequest extends MaxRequest {

    private final @Param(type = ParamType.QUERY) Integer count;
    private final @Param(type = ParamType.QUERY) Long marker;

    private ChatsGetRequest(Builder builder) {
        count = builder.count;
        marker = builder.marker;
    }

    public static final class Builder {
        private Integer count;
        private Long marker;

        public Builder() {
        }

        public Builder count(Integer count) {
            this.count = count;
            return this;
        }

        public Builder marker(Long marker) {
            this.marker = marker;
            return this;
        }

        public ChatsGetRequest build() {
            return new ChatsGetRequest(this);
        }
    }
}
