package ru.floerka.max.core.models.response.subscriptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.floerka.max.core.api.objects.MaxObject;
import ru.floerka.max.core.models.subscriptions.Subscription;

@AllArgsConstructor
@Getter
public class GetSubscriptionsResponse extends MaxObject {

    private final Subscription[] subscriptions;
}
