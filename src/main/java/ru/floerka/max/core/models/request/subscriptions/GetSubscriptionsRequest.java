package ru.floerka.max.core.models.request.subscriptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import ru.floerka.max.core.api.objects.ApiEndpoint;
import ru.floerka.max.core.api.objects.HttpMethod;
import ru.floerka.max.core.api.objects.MaxRequest;
import ru.floerka.max.core.models.response.subscriptions.GetSubscriptionsResponse;

@AllArgsConstructor
@Getter
@ApiEndpoint(path = "subscriptions", method = HttpMethod.GET, response = GetSubscriptionsResponse.class)
public class GetSubscriptionsRequest extends MaxRequest {
}
