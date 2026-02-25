package ru.floerka.max.core.models.request.subscriptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.floerka.max.core.api.objects.ApiEndpoint;
import ru.floerka.max.core.api.objects.HttpMethod;
import ru.floerka.max.core.api.objects.MaxObject;
import ru.floerka.max.core.api.queries.Param;
import ru.floerka.max.core.models.response.messages.EditMessageResponse;
import ru.floerka.max.core.models.response.subscriptions.AddSubscriptionsResponse;

@AllArgsConstructor
@Getter
@ApiEndpoint(path = "subscriptions", method = HttpMethod.POST, response = AddSubscriptionsResponse.class)
public class AddSubscriptionsRequest extends MaxObject {

    private final @Param String url;
    private final @Param String[] updateTypes;
    private final @Param String secret;
}
