package ru.floerka.max.core.models.request.subscriptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.floerka.max.core.api.objects.ApiEndpoint;
import ru.floerka.max.core.api.objects.HttpMethod;
import ru.floerka.max.core.api.objects.MaxObject;
import ru.floerka.max.core.api.objects.MaxRequest;
import ru.floerka.max.core.api.queries.Param;
import ru.floerka.max.core.api.queries.ParamType;
import ru.floerka.max.core.models.response.subscriptions.AddSubscriptionsResponse;
import ru.floerka.max.core.models.response.subscriptions.RemoveSubscriptionsResponse;

@AllArgsConstructor
@Getter
@ApiEndpoint(path = "subscriptions", method = HttpMethod.POST, response = RemoveSubscriptionsResponse.class)
public class RemoveSubscriptionsRequest extends MaxRequest {

    private final @Param(type = ParamType.QUERY) String url;

    private RemoveSubscriptionsRequest(Builder builder) {
        url = builder.url;
    }

    public static final class Builder {
        private String url;

        public Builder() {
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public RemoveSubscriptionsRequest build() {
            return new RemoveSubscriptionsRequest(this);
        }
    }
}
