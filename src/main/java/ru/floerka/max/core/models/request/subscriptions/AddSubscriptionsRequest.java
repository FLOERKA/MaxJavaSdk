package ru.floerka.max.core.models.request.subscriptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.floerka.max.core.api.objects.MaxObject;
import ru.floerka.max.core.api.queries.Param;

@AllArgsConstructor
@Getter
public class AddSubscriptionsRequest extends MaxObject {

    private final @Param String url;
    private final @Param String[] updateTypes;
    private final @Param String secret;
}
