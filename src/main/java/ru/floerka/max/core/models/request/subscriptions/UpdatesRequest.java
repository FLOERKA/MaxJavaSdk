package ru.floerka.max.core.models.request.subscriptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;
import ru.floerka.max.core.api.objects.ApiEndpoint;
import ru.floerka.max.core.api.objects.HttpMethod;
import ru.floerka.max.core.api.objects.MaxObject;
import ru.floerka.max.core.api.queries.Param;
import ru.floerka.max.core.api.queries.ParamType;
import ru.floerka.max.core.models.response.subscriptions.AddSubscriptionsResponse;
import ru.floerka.max.core.models.response.subscriptions.UpdatesResponse;

@AllArgsConstructor
@Getter
@Builder
@ApiEndpoint(path = "updates", method = HttpMethod.POST, response = UpdatesResponse.class)
public class UpdatesRequest extends MaxObject {

    private final @Param(require = false, type = ParamType.QUERY) Integer limit;
    private final @Param(require = false, type = ParamType.QUERY) Integer timeout;
    private final @Param(require = false, type = ParamType.QUERY) Long marker;
    private final @Param(require = false, type = ParamType.QUERY) String[] types;

    private UpdatesRequest(Builder builder) {
        limit = builder.limit;
        timeout = builder.timeout;
        marker = builder.marker;
        types = builder.types;
    }

    public static final class Builder {
        private Integer limit;
        private Integer timeout;
        private Long marker;
        private String[] types;

        public Builder() {
        }

        public Builder limit(Integer limit) {
            this.limit = limit;
            return this;
        }

        public Builder timeout(Integer timeout) {
            this.timeout = timeout;
            return this;
        }

        public Builder marker(Long marker) {
            this.marker = marker;
            return this;
        }

        public Builder types(String... types) {
            this.types = types;
            return this;
        }

        public UpdatesRequest build() {
            return new UpdatesRequest(this);
        }
    }
}
