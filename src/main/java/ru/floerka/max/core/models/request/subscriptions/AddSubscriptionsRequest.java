package ru.floerka.max.core.models.request.subscriptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.floerka.max.core.api.objects.ApiEndpoint;
import ru.floerka.max.core.api.objects.HttpMethod;
import ru.floerka.max.core.api.objects.MaxObject;
import ru.floerka.max.core.api.objects.MaxRequest;
import ru.floerka.max.core.api.queries.Param;
import ru.floerka.max.core.models.response.messages.EditMessageResponse;
import ru.floerka.max.core.models.response.subscriptions.AddSubscriptionsResponse;

@AllArgsConstructor
@Getter
@ApiEndpoint(path = "subscriptions", method = HttpMethod.POST, response = AddSubscriptionsResponse.class)
public class AddSubscriptionsRequest extends MaxRequest {

    private final @Param String url;
    private final @Param String[] updateTypes;
    private final @Param String secret;

    private AddSubscriptionsRequest(Builder builder) {
        url = builder.url;
        updateTypes = builder.updateTypes;
        secret = builder.secret;
    }

    public static final class Builder {
        private String url;
        private String[] updateTypes;
        private String secret;

        public Builder() {
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder updateTypes(String[] updateTypes) {
            this.updateTypes = updateTypes;
            return this;
        }

        public Builder secret(String secret) {
            this.secret = secret;
            return this;
        }

        public AddSubscriptionsRequest build() {
            return new AddSubscriptionsRequest(this);
        }
    }
}
